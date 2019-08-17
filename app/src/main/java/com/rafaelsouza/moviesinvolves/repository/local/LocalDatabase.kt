package com.rafaelsouza.moviesinvolves.repository.local

import android.arch.persistence.db.SupportSQLiteOpenHelper
import android.arch.persistence.room.*
import com.rafaelsouza.moviesinvolves.repository.model.Genre
import com.rafaelsouza.moviesinvolves.repository.model.Movie

@Database(entities = [Movie::class, Genre::class], version = 2)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    override fun clearAllTables() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createOpenHelper(config: DatabaseConfiguration?): SupportSQLiteOpenHelper {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createInvalidationTracker(): InvalidationTracker {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}