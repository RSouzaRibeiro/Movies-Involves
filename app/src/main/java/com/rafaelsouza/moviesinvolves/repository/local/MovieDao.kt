package com.rafaelsouza.moviesinvolves.repository.local

import android.arch.persistence.room.*
import com.rafaelsouza.moviesinvolves.repository.model.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie): Long

    @Update
    fun updateMovie(movie: Movie)

    @Delete
    fun deleteMovie(movie: Movie)

    @Query("SELECT * FROM movie ")
    fun getAllMovie(): List<Movie>

    @Query("SELECT * FROM movie WHERE id = :arg")
    fun getMovie(arg: Int): Movie

}