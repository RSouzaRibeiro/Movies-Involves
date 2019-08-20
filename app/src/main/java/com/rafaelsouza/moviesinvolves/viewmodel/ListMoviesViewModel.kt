package com.rafaelsouza.moviesinvolves.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.rafaelsouza.moviesinvolves.extension.androidSubscribe
import com.rafaelsouza.moviesinvolves.repository.local.LocalDatabase
import com.rafaelsouza.moviesinvolves.repository.model.Movie
import com.rafaelsouza.moviesinvolves.repository.request.MoviesRequest
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
                    movies.value = it
                    it.results?.forEach { it -> insertMovieLocal(it) }
                },
                {
                    getAllMoviesLocal()
                }
            ))

    }

    fun getSearchMovies(page: Int=1,search: String) {
        if (search.length > 2) {
            disposables.add(service.search(page,search)
                .debounce(500, TimeUnit.MILLISECONDS)
                .androidSubscribe()
                .doOnSubscribe { progress.value = true }
                .doFinally { progress.value = false }
                .subscribe(
                    {
                        movies.value = it
                        it.results?.forEach { it -> insertMovieLocal(it) }
                    },
                    {
                        error.value = "Ops! Tivemos um problema!"
                    }
                ))
        }


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

    fun insertMovieLocal(movie: Movie) {
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


}