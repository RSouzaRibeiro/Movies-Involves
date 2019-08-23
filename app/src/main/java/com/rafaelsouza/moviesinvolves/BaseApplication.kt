package com.rafaelsouza.moviesinvolves

import android.app.Application
import com.rafaelsouza.moviesinvolves.module.ApiComponent
import com.rafaelsouza.moviesinvolves.module.ApiModule
import com.rafaelsouza.moviesinvolves.module.DaggerApiComponent

class BaseApplication: Application() {

    lateinit var graph: ApiComponent

    override fun onCreate() {
        super.onCreate()

        graph = DaggerApiComponent.builder().apiModule(ApiModule(this)).build()
    }
}