package com.rafaelsouza.moviesinvolves.repository

import com.rafaelsouza.moviesinvolves.repository.request.MoviesRequest
import com.rafaelsouza.moviesinvolves.repository.model.Movie
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface Service {

    @GET("movie/upcoming")
    fun listAllMovies(): Observable<MoviesRequest>
}