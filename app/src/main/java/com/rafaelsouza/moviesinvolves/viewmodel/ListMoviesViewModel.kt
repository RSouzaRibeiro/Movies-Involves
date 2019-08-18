package com.rafaelsouza.moviesinvolves.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.SharedPreferences
import com.rafaelsouza.moviesinvolves.extension.androidSubscribe
import com.rafaelsouza.moviesinvolves.repository.local.LocalDatabase
import com.rafaelsouza.moviesinvolves.repository.model.Movie
import com.rafaelsouza.moviesinvolves.repository.request.MoviesRequest
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.collections.ArrayList

class ListMoviesViewModel : BaseViewModel {

    @Inject
    constructor()

    @Inject
    lateinit var localDb: LocalDatabase


    var movies = MutableLiveData<MoviesRequest>()
    var progress = MutableLiveData<Boolean>()
    var error = MutableLiveData<String>()

    fun getMovies(page: Int =1) {
        disposables.add(service.listAllMovies(page)
            .androidSubscribe()
            .doOnSubscribe { progress.value = true }
            .doFinally { progress.value = false }
            .subscribe(
                {
                    it.results?.forEach { it -> insertMovies(it) }
                    movies.value = it

                },
                {

                    getAllMoviesLocal()

                }
            ))

    }

    fun getSearchMovies(search: String) {
        if (search.length > 2) {
            disposables.add(service.search(search)
                .debounce(500, TimeUnit.MILLISECONDS)
                .androidSubscribe()
                .doOnSubscribe { progress.value = true }
                .doFinally { progress.value = false }
                .subscribe(
                    {
                        movies.value = it
                        it.results?.forEach { it -> insertMovies(it) }
                    },
                    {
                        error.value = it.localizedMessage

                    }
                ))
        }


    }


    private fun insertMovies(movie: Movie) {
        disposables.add(
            Single
                .fromCallable { localDb.movieDao().insertMovie(movie) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                },
                    {
                        error.value = it.localizedMessage
                    })
        )
    }


    fun getAllMoviesLocal() {
        disposables.add(
            Single
                .fromCallable { localDb.movieDao().getAllMovie() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    movies.value = MoviesRequest(it)
                },
                    {
                        error.value = it.localizedMessage
                    })
        )
    }


}