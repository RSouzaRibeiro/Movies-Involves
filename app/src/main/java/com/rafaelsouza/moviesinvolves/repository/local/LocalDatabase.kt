package com.rafaelsouza.moviesinvolves.repository.local

import android.arch.persistence.room.*
import com.rafaelsouza.moviesinvolves.repository.model.Converters
import com.rafaelsouza.moviesinvolves.repository.model.Genre
import com.rafaelsouza.moviesinvolves.repository.model.Movie

@Database(entities = [Movie::class, Genre::class], version = 5)

@TypeConverters(Converters::class)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao



}