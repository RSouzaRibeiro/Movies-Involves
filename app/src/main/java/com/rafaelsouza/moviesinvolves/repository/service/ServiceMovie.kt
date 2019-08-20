package com.rafaelsouza.moviesinvolves.repository.service

import com.rafaelsouza.moviesinvolves.repository.request.MoviesRequest
import com.rafaelsouza.moviesinvolves.repository.model.Movie
import io.reactivex.Observable

import retrofit2.http.*
import java.util.*

interface ServiceMovie {

    @GET("movie/upcoming")
    fun listAllMovies(@Query("page") page: Int = 1,
                      @Query("language") language: String = Locale.getDefault().toLanguageTag()): Observable<MoviesRequest>

    @GET("search/movie")
    fun search(
        @Query("page") page: Int = 1,
        @Query("query") query: String,
        @Query("language") language: String = Locale.getDefault().toLanguageTag()
    ): Observable<MoviesRequest>

    @GET("movie/{movie_id}")
    fun getMovieById(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = Locale.getDefault().toLanguageTag()

    ): Observable<Movie>
}