package com.rafaelsouza.moviesinvolves.repository.request

import com.google.gson.annotations.SerializedName
import com.rafaelsouza.moviesinvolves.repository.model.Movie

class MoviesRequest {

    constructor(results: List<Movie>) {
        this.results = results
        this.page = 1

    }


    @SerializedName("results")
    var results: List<Movie>? =null

    @SerializedName("page")
    var page : Int? =null


}