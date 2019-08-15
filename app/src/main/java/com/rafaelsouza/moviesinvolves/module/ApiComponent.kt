package com.rafaelsouza.moviesinvolves.module

import com.rafaelsouza.moviesinvolves.view.ListMoviesActivity
import com.rafaelsouza.moviesinvolves.view.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(activity: MainActivity)
    fun inject(activity: ListMoviesActivity)
}