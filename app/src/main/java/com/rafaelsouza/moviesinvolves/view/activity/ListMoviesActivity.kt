package com.rafaelsouza.moviesinvolves.view.activity

import android.app.Activity
import android.app.Dialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.Menu
import android.view.Window
import android.widget.Button
import android.widget.SearchView
import android.widget.TextView
import com.rafaelsouza.moviesinvolves.BaseApplication
import com.rafaelsouza.moviesinvolves.R
import com.rafaelsouza.moviesinvolves.extension.showDialogError
import com.rafaelsouza.moviesinvolves.repository.model.Movie
import com.rafaelsouza.moviesinvolves.view.adapter.MovieAdapter
import com.rafaelsouza.moviesinvolves.viewmodel.ListMoviesViewModel
import com.rafaelsouza.moviesinvolves.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_list_movies.*
import javax.inject.Inject


class ListMoviesActivity : AppCompatActivity() {


    companion object {
        const val EXTRA_SEARCH = "mSearch"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<ListMoviesViewModel>
    var viewModel: ListMoviesViewModel? = null
    var mSearch: String? = ""
    var currentPage = 1
    lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_movies)
        (application as BaseApplication).graph.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ListMoviesViewModel::class.java)
        doBinds()
        swipeRefreshConfig()
        getMovies()
    }

    private fun doBinds() {
        viewModel?.movies?.observe(this, Observer {
            currentPage = it?.page!!
            if (currentPage == 1) {
                it?.results?.let { it ->
                    initRecycleView(it)
                }
            } else {
                adapter.add(it?.results!!)
            }

        })

        viewModel?.progress?.observe(this, Observer {
            swipeRefresh.isRefreshing = it!!
        })

        viewModel?.error?.observe(this, Observer {
            Dialog(this).showDialogError(this, it!!)
        })
    }

    private fun swipeRefreshConfig() {
        swipeRefresh.setOnRefreshListener {
            getMovies()
        }
    }

    private fun getMovies(page: Int = 1) {
        if (mSearch.isNullOrEmpty())
            viewModel?.getMovies(page)
        else {
            viewModel?.getSearchMovies(page, mSearch!!)
        }
    }

    private fun initRecycleView(result: List<Movie>) {
        var layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
        adapter = MovieAdapter(this, result)
        recyclerView.adapter = adapter
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val lastPosition = layoutManager.findLastVisibleItemPositions(null)
                if (lastPosition[0] >= (recyclerView.adapter?.itemCount?.minus(3)!!)) {
                    getMovies(currentPage + 1)
                }


            }
        })
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
                    getMovies()
                }
                return false
            }

            override fun onQueryTextChange(search: String?): Boolean {
                if (!search.isNullOrEmpty()) {
                    mSearch = search
                    getMovies()
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
