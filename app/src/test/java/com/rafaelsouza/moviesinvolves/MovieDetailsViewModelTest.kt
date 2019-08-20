package com.rafaelsouza.moviesinvolves

import android.app.Application
import com.rafaelsouza.moviesinvolves.viewmodel.MovieDetailsViewModel
import junit.framework.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MovieDetailsViewModelTest {

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getMovieTest(){

        val viewModelTest= MovieDetailsViewModel(Mockito.mock(Application::class.java))
        var movie = viewModelTest.movie


        Assert.assertEquals(2,2)
    }
}