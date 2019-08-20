package com.rafaelsouza.moviesinvolves.view.activity

import android.app.Dialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.rafaelsouza.moviesinvolves.BaseApplication
import com.rafaelsouza.moviesinvolves.R
import com.rafaelsouza.moviesinvolves.extension.showDialogSucess

import com.rafaelsouza.moviesinvolves.repository.model.Movie
import com.rafaelsouza.moviesinvolves.util.Utils
import com.rafaelsouza.moviesinvolves.viewmodel.MovieDetailsViewModel
import com.rafaelsouza.moviesinvolves.viewmodel.ViewModelFactory
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_details.*


import javax.inject.Inject


class MovieDetailsActivity : AppCompatActivity() {

    companion object {
        var MOVIE_ID = "movieId"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<MovieDetailsViewModel>
    var viewModel: MovieDetailsViewModel? = null
    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        (application as BaseApplication).graph.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieDetailsViewModel::class.java)
        initToolbar()
        doBinds()
        setListners()
        intent.extras.get(MOVIE_ID)?.let {
            viewModel?.getMovieById(it.toString(), getString(R.string.API_KEY))
        }
    }

    private fun doBinds() {
        viewModel?.movie?.observe(this, Observer {
            it?.let {
                    it ->
                initView(it)
                movie = it
            }
        })

        viewModel?.progress?.observe(this, Observer { isProgress ->
            if (isProgress!!) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        })

        viewModel?.sucess?.observe(this, Observer {
                Dialog(this).showDialogSucess(this, it!!)
        })

        viewModel?.error?.observe(this, Observer {

        })
    }

    private fun initToolbar() {
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun initView(movie: Movie) {
        movie.backdropPath?.let { setPoster(it) }
        supportActionBar?.title = movie.title
        txtRatingNumber.text = movie.voteAverage.toString()
        txtReleaseDate.text = Utils().formatDate(movie.releaseDate)
        txtSinopse.text = movie.overview
        txtRumtimeInfo.text = Utils().convertMinutsToHour(movie.runtime!!)
        txtBudgetInfo.text = Utils().currencyFormat(movie.budget.toString())
        txtRevenueInfo.text = Utils().currencyFormat(movie.revenue.toString())
        txtVotesInfo.text = movie.votes.toString()
        txtGenreInfo.text = movie.getAllGenteToString()


    }

    private fun setPoster(imagePath: String) {
        Picasso.with(this)
            .load(getString(R.string.PATH_GET_IMAGE) + imagePath)
            .placeholder(R.mipmap.placeholder_movie)
            .resize(320, 230)
            .networkPolicy(NetworkPolicy.NO_STORE)
            .centerCrop()
            .into(imgPosterMovie)


    }

    private fun setListners() {
        btnFloatFavorite.setOnClickListener {
            if(movie!=null)
            viewModel?.insertMovieLocal(movie)
        }
    }


}
