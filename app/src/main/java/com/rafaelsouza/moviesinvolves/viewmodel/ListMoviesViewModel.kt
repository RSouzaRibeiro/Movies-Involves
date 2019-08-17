package com.rafaelsouza.moviesinvolves.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.content.SharedPreferences
import com.rafaelsouza.moviesinvolves.repository.local.LocalDatabase
import com.rafaelsouza.moviesinvolves.repository.model.Movie
import com.rafaelsouza.moviesinvolves.repository.request.MoviesRequest
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ListMoviesViewModel : BaseViewModel {

    @Inject
    constructor()

    @Inject
    lateinit var localDb: LocalDatabase

    @Inject
    lateinit var sharedPref: SharedPreferences

    var movies = MutableLiveData<MoviesRequest>()
    var progress = MutableLiveData<Boolean>()
    var error = MutableLiveData<String>()

    fun getMovies() {
        disposables.add(service.listAllMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { progress.value = true }
            .doFinally { progress.value = false }
            .subscribe(
                {
                    movies.value = it
                },
                {
                    error.value = it.localizedMessage
                }
            ))

    }

    fun getSearchMovies(search: String) {
        disposables.add(service.search(search)
            .debounce(500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { progress.value = true }
            .doFinally { progress.value = false }
            .subscribe(
                {
                    movies.value = it
                    it.results?.let { it -> insertUser(it) }
                },
                {
                    error.value = it.localizedMessage

                }
            ))

    }


    private fun insertUser(movie: ArrayList<Movie>) {
        disposables.add(
            Single
                .fromCallable { localDb.movieDao().insertAllMovie(movie) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                }, {})
        )
    }
}