package com.rafaelsouza.moviesinvolves.module

import com.rafaelsouza.moviesinvolves.view.activity.ListMoviesActivity
import com.rafaelsouza.moviesinvolves.view.activity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(activity: MainActivity)
    fun inject(activity: ListMoviesActivity)
}