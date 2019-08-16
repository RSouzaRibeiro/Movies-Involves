package com.rafaelsouza.moviesinvolves.repository

import com.rafaelsouza.moviesinvolves.repository.request.MoviesRequest
import com.rafaelsouza.moviesinvolves.repository.model.Movie
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.*

interface Service {

    @GET("movie/upcoming")
    fun listAllMovies(): Observable<MoviesRequest>

    @GET("search/movie")
    fun search(@Query("query") query: String): Observable<MoviesRequest>

    @GET("movie/{movie_id}")
    fun getMovieById(@Path("movie_id") movieId : String,
                     @Query("api_key") apiKey: String): Observable<Movie>
}