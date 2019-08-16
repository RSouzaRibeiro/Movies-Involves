package com.rafaelsouza.moviesinvolves.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.rafaelsouza.moviesinvolves.BaseApplication
import com.rafaelsouza.moviesinvolves.R
import com.rafaelsouza.moviesinvolves.repository.model.Movie
import com.rafaelsouza.moviesinvolves.viewmodel.MovieDetailsViewModel
import com.rafaelsouza.moviesinvolves.viewmodel.ViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.item_movie.*
import java.text.ParseException
import java.text.SimpleDateFormat
import javax.inject.Inject


class MovieDetailsActivity : AppCompatActivity() {

    companion object {
        var MOVIE_ID = "movieId"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<MovieDetailsViewModel>
    var viewModel: MovieDetailsViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        (application as BaseApplication).graph.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieDetailsViewModel::class.java)
        doBinds()
        intent.extras.get(MOVIE_ID)?.let {
            viewModel?.getMovieById(it.toString(), getString(R.string.API_KEY))
        }

    }

    private fun doBinds() {
        viewModel?.movie?.observe(this, Observer {
            it?.let { it -> initView(it) }
        })

        viewModel?.progress?.observe(this, Observer { isProgress ->
            if(isProgress!!){
               progressBar.visibility = View.VISIBLE
           }else{
               progressBar.visibility = View.GONE
           }
        })

        viewModel?.error?.observe(this, Observer {

        })
    }


    private fun initView(movie: Movie) {
        movie.backdropPath?.let { setPoster(it) }
        titleTXT.text = movie.title



    }

    private fun setPoster(imagePath: String) {
        Picasso.with(this)
            .load(getString(R.string.PATH_GET_IMAGE) + imagePath)
            .placeholder(R.mipmap.placeholder_movie)
            .resize(320,250)
            .centerCrop()
            .into(posterMovieIMG)


    }


}
