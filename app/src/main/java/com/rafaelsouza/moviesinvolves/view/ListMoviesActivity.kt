package com.rafaelsouza.moviesinvolves.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.rafaelsouza.moviesinvolves.BaseApplication
import com.rafaelsouza.moviesinvolves.R
import com.rafaelsouza.moviesinvolves.viewmodel.ListMoviesViewModel
import com.rafaelsouza.moviesinvolves.viewmodel.ViewModelFactory
import javax.inject.Inject

class ListMoviesActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<ListMoviesViewModel>
    var viewModel: ListMoviesViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_movies)
        (application as BaseApplication).graph.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ListMoviesViewModel::class.java)
        doBinds()
        viewModel?.getMovies()
    }

    private fun doBinds(){
        viewModel?.movies?.observe(this, Observer {
            if(it!=null){
                Toast.makeText(this, it?.results?.get(0)?.title, Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this, "Não Foi", Toast.LENGTH_LONG).show()
            }

        })

        viewModel?.progress?.observe(this, Observer {

        })

        viewModel?.error?.observe(this, Observer {

        })
    }
}
