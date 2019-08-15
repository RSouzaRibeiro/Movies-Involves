package com.rafaelsouza.moviesinvolves.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.content.SharedPreferences
import com.rafaelsouza.moviesinvolves.repository.local.LocalDatabase
import com.rafaelsouza.moviesinvolves.repository.model.Movie
import com.rafaelsouza.moviesinvolves.repository.request.MoviesRequest
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListMoviesViewModel: BaseViewModel {

    @Inject
    constructor()

    @Inject
    lateinit var localDb: LocalDatabase

    @Inject
    lateinit var sharedPref: SharedPreferences

    var movies = MutableLiveData<MoviesRequest>()
    var progress = MutableLiveData<Boolean>()
    var error = MutableLiveData<String>()

    fun getMovies(){
        disposables.add(service.listAllMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe{progress.value =true }
            .doFinally{ progress.value =false }
            .subscribe(
                {
                    movies.value = it
                },
                {
                    error.value = it.localizedMessage
                }
            ))

    }
}