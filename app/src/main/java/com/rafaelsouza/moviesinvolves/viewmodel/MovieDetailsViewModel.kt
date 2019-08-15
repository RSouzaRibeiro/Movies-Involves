package com.rafaelsouza.moviesinvolves.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.content.SharedPreferences
import com.rafaelsouza.moviesinvolves.repository.local.LocalDatabase
import com.rafaelsouza.moviesinvolves.repository.model.Movie
import javax.inject.Inject

class MovieDetailsViewModel: BaseViewModel {

    @Inject
    constructor()


    @Inject
    lateinit var sharedPref: SharedPreferences

    var movie = MutableLiveData<Movie>()
    var status = MutableLiveData<Boolean>()
    var error = MutableLiveData<String>()



}