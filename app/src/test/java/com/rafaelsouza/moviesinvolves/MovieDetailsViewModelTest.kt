package com.rafaelsouza.moviesinvolves

import android.app.Application
import android.arch.lifecycle.ViewModelProviders
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.provider.DocumentsContract
import com.rafaelsouza.moviesinvolves.repository.local.LocalDatabase
import com.rafaelsouza.moviesinvolves.repository.model.Movie
import com.rafaelsouza.moviesinvolves.viewmodel.ListMoviesViewModel
import com.rafaelsouza.moviesinvolves.viewmodel.MovieDetailsViewModel
import com.rafaelsouza.moviesinvolves.viewmodel.ViewModelFactory
import junit.framework.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import javax.inject.Inject


class MovieDetailsViewModelTest {



    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

    }

    @Test
    fun getMovieTest() {

        val viewModelTest = MovieDetailsViewModel(Mockito.mock(Application::class.java))


        var movie = Movie()
        movie.apply {
            title = "Filme Teste"

        }
        viewModelTest.movie.value = movie



        Assert.assertEquals("Filme Teste", viewModelTest.movie.value?.title)
    }
}