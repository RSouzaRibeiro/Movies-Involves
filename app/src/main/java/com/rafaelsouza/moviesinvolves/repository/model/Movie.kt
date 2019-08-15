package com.rafaelsouza.moviesinvolves.repository.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "movie")
class Movie {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    var id: Int? = null

    @ColumnInfo(name = "title")
    @SerializedName("title")
    var title: String = ""

    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    var release_date: String = ""

    /*@ColumnInfo(name = "genre_ids")
    @SerializedName("genre_ids")
    var genres: ArrayList<String>? = null*/
}