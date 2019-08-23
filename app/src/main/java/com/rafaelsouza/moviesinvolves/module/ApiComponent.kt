package com.rafaelsouza.moviesinvolves.module

import com.rafaelsouza.moviesinvolves.view.activity.ListMoviesActivity
import com.rafaelsouza.moviesinvolves.view.activity.MovieDetailsActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(activity: ListMoviesActivity)
    fun inject(activity: MovieDetailsActivity)
}