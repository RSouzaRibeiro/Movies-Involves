package com.rafaelsouza.moviesinvolves.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.content.SharedPreferences
import com.rafaelsouza.moviesinvolves.R
import com.rafaelsouza.moviesinvolves.extension.androidSubscribe
import com.rafaelsouza.moviesinvolves.repository.local.LocalDatabase
import com.rafaelsouza.moviesinvolves.repository.model.Movie
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieDetailsViewModel : BaseViewModel {

    @Inject
    constructor()

    constructor(p0: Any)

    @Inject
    lateinit var localDb: LocalDatabase

    var movie = MutableLiveData<Movie>()
    var progress = MutableLiveData<Boolean>()
    var error = MutableLiveData<String>()
    var sucess = MutableLiveData<String>()


    fun getMovieById(movieId: String, apiKey: String) {
        disposables.add(service.getMovieById(movieId, apiKey)
            .androidSubscribe()
            .doOnSubscribe { progress.value = true }
            .doFinally { progress.value = false }
            .subscribe(
                {
                    movie.value = it
                },
                {
                    getMoviesLocal(movieId.toInt())
                }
            ))

    }

    fun getMoviesLocal(id: Int) {
        disposables.add(
            Single
                .fromCallable { localDb.movieDao().getMovie(id) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    movie.value = it
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
                    sucess.value = "Filme adicionado aos favoritos!"
                },
                    {
                        error.value = it.localizedMessage
                    })
        )
    }


}