package com.rafaelsouza.moviesinvolves.view.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.rafaelsouza.moviesinvolves.R

class MovieDetailsActivity : AppCompatActivity() {

    companion object {
        var MOVIE_ID = "movieId"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
    }
}
