package com.rafaelsouza.moviesinvolves.repository.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "genre")
class Genre {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    var id: Int? = null

    @ColumnInfo(name = "name")
    @SerializedName("name")
    var name: String? = null


}