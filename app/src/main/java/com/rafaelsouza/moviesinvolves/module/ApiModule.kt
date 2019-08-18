package com.rafaelsouza.moviesinvolves.module

import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.preference.PreferenceManager
import android.support.annotation.RequiresApi
import com.rafaelsouza.moviesinvolves.R
import com.rafaelsouza.moviesinvolves.repository.Ambiente
import com.rafaelsouza.moviesinvolves.repository.Service
import com.rafaelsouza.moviesinvolves.repository.local.LocalDatabase
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import javax.inject.Singleton

@Module
class ApiModule(val context: Context) {

    @Provides
    fun getAmbiente(): Ambiente = Ambiente.DESENVOLVIMENTO


    @Provides
    @Singleton
    fun getRetrofit(): Retrofit {
        val ambiente = getAmbiente()

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor {
                val requestBuilder = it.request().newBuilder()
                requestBuilder.header("Accept", "application/json")
                requestBuilder.header("Authorization", "Bearer " + context.getString(R.string.API_KEY))
                it.proceed(requestBuilder.build())
            }
            .build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(ambiente.getUrl())
            .client(httpClient)
            .build()
    }

    @Provides
    fun getClient(): Service = getRetrofit().create(Service::class.java)

    @Provides
    @Singleton
    fun getDatabase(): LocalDatabase =
        Room.databaseBuilder(context.applicationContext, LocalDatabase::class.java, "local_storage").build()



}