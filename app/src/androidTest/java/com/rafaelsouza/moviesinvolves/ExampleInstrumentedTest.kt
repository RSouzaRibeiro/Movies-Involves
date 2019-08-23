package com.rafaelsouza.moviesinvolves

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.rafaelsouza.moviesinvolves.repository.local.LocalDatabase
import com.rafaelsouza.moviesinvolves.repository.model.Genre
import com.rafaelsouza.moviesinvolves.repository.model.Movie
import com.rafaelsouza.moviesinvolves.viewmodel.MovieDetailsViewModel
import io.reactivex.schedulers.TestScheduler
import junit.framework.Assert

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.util.concurrent.TimeUnit

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    lateinit var  appContext :Context
    lateinit var   viewModelTest: MovieDetailsViewModel
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
         appContext = InstrumentationRegistry.getTargetContext()
         viewModelTest = MovieDetailsViewModel(Mockito.mock(Application::class.java))
        viewModelTest.localDb = Room.inMemoryDatabaseBuilder(appContext, LocalDatabase::class.java).build()

    }

    @Test
    fun insertMovieTest() {
        val movie = Movie().apply {
            id = 123
            title= "Filme Teste"
            genres = ArrayList<Genre>()
            releaseDate = ""

        }
       val movieId =  viewModelTest.localDb.movieDao().insertMovie(movie)
        Assert.assertEquals(movie.id.toLong(), movieId)
    }

    @Test
    fun getMovieTest() {
        val movie = Movie().apply {
            id = 123
            title= "Filme Teste"
            genres = ArrayList<Genre>()
            releaseDate = ""

        }
        viewModelTest.localDb.movieDao().insertMovie(movie)
        val returnedMovie = viewModelTest.localDb.movieDao().getMovie(movie.id)
        Assert.assertEquals(movie.id, returnedMovie.id)
    }


    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.rafaelsouza.moviesinvolves", appContext.packageName)
    }
}
