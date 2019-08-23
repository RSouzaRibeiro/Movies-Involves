package com.rafaelsouza.moviesinvolves.viewmodel

import android.arch.lifecycle.ViewModel
import android.util.Log
import com.rafaelsouza.moviesinvolves.repository.service.ServiceMovie
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseViewModel: ViewModel() {

    var disposables = CompositeDisposable()

    @Inject
    lateinit var service: ServiceMovie

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
        Log.d("ViewModel", "onCleared")
    }
}