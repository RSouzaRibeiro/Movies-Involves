package com.rafaelsouza.moviesinvolves.repository.request

import com.google.gson.annotations.SerializedName
import com.rafaelsouza.moviesinvolves.repository.model.Movie

class MoviesRequest {

    constructor(results: ArrayList<Movie>) {
        this.results = results

    }


    @SerializedName("results")
    var results: ArrayList<Movie>? =null

    @SerializedName("page")
    var page : Int? =null


}