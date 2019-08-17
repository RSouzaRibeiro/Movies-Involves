package com.rafaelsouza.moviesinvolves.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.Menu
import android.widget.ImageView
import android.widget.SearchView

import com.rafaelsouza.moviesinvolves.BaseApplication
import com.rafaelsouza.moviesinvolves.R

import com.rafaelsouza.moviesinvolves.repository.model.Movie
import com.rafaelsouza.moviesinvolves.view.adapter.MovieAdapter
import com.rafaelsouza.moviesinvolves.viewmodel.ListMoviesViewModel
import com.rafaelsouza.moviesinvolves.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_list_movies.*
import javax.inject.Inject


class ListMoviesActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<ListMoviesViewModel>
    var viewModel: ListMoviesViewModel? = null
    var mSearch: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_movies)
        (application as BaseApplication).graph.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ListMoviesViewModel::class.java)
        doBinds()
        swipeRefreshConfig()
        viewModel?.getMovies()
    }

    private fun doBinds() {
        viewModel?.movies?.observe(this, Observer {
            it?.results?.let { it -> initRecycleView(it) }

        })

        viewModel?.progress?.observe(this, Observer {
            swipeRefresh.isRefreshing = it!!
        })

        viewModel?.error?.observe(this, Observer {

        })
    }

    private fun swipeRefreshConfig() {
        swipeRefresh.setOnRefreshListener {
            if (mSearch.isNullOrEmpty())
                viewModel?.getMovies()
            else {
                viewModel?.getSearchMovies(mSearch!!)
            }
        }
    }

    private fun initRecycleView(result: ArrayList<Movie>) {
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.adapter = MovieAdapter(this, result)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_movie_list, menu)
        val itemSearch = menu?.findItem(R.id.menuSearch)
        val searchView = itemSearch?.actionView as SearchView
        searchView.queryHint = getString(R.string.label_seach_code)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(search: String?): Boolean {
                if (!search.isNullOrEmpty()) {
                    mSearch = search
                    viewModel?.getSearchMovies(search)
                }
                return false
            }

            override fun onQueryTextChange(search: String?): Boolean {
                if (!search.isNullOrEmpty()) {
                    mSearch = search
                    viewModel?.getSearchMovies(search)
                }

                return false
            }

        })

        searchView.setOnCloseListener {
            mSearch = ""
            viewModel?.getMovies()
            false
        }

        return super.onCreateOptionsMenu(menu)
    }
}
