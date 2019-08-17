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
    var releaseDate: String = ""

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    var imagePath: String? = null

    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    var backdropPath: String? = null

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    var voteAverage: Float? = null

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    var overview: String? = null

    @ColumnInfo(name = "runtime")
    @SerializedName("runtime")
    var runtime: Long? = null

    @ColumnInfo(name = "budget")
    @SerializedName("budget")
    var budget: Long? = null

    @ColumnInfo(name = "revenue")
    @SerializedName("revenue")
    var revenue: Long? = null

    @ColumnInfo(name = "vote_count")
    @SerializedName("vote_count")
    var votes: Long? = null






    /*@ColumnInfo(name = "genres")
    @SerializedName("genres")
    var genres: ArrayList<Genre>? = null*/
}