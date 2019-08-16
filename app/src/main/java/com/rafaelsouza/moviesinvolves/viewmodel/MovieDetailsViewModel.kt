package com.rafaelsouza.moviesinvolves.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.content.SharedPreferences
import com.rafaelsouza.moviesinvolves.repository.local.LocalDatabase
import com.rafaelsouza.moviesinvolves.repository.model.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieDetailsViewModel: BaseViewModel {

    @Inject
    constructor()


    @Inject
    lateinit var sharedPref: SharedPreferences

    var movie = MutableLiveData<Movie>()
    var progress = MutableLiveData<Boolean>()
    var error = MutableLiveData<String>()


    fun getMovieById(movieId: String, apiKey:String){
        disposables.add(service.getMovieById(movieId, apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe{progress.value =true }
            .doFinally{ progress.value =false }
            .subscribe(
                {
                    movie.value = it
                },
                {
                    error.value = it.localizedMessage
                }
            ))

    }



}