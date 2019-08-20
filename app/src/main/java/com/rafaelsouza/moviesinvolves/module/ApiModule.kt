package com.rafaelsouza.moviesinvolves.module

import android.arch.persistence.room.Room
import android.content.Context
import com.rafaelsouza.moviesinvolves.BuildConfig
import com.rafaelsouza.moviesinvolves.R
import com.rafaelsouza.moviesinvolves.repository.service.ServiceMovie
import com.rafaelsouza.moviesinvolves.repository.local.LocalDatabase
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule(private val context: Context) {




    @Provides
    @Singleton
    fun getRetrofit(): Retrofit {
        val ambiente = BuildConfig.SERVER_URL

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
            .baseUrl(ambiente)
            .client(httpClient)
            .build()
    }

    @Provides
    fun getClient(): ServiceMovie = getRetrofit().create(ServiceMovie::class.java)

    @Provides
    @Singleton
    fun getDatabase(): LocalDatabase =
        Room.databaseBuilder(context.applicationContext, LocalDatabase::class.java, "local_storage").build()



}