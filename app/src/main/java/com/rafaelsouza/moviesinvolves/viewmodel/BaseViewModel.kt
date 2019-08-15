package com.rafaelsouza.moviesinvolves.viewmodel

import android.arch.lifecycle.ViewModel
import android.util.Log
import com.rafaelsouza.moviesinvolves.repository.Service
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseViewModel: ViewModel() {

    var disposables = CompositeDisposable()

    @Inject
    lateinit var service: Service

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
        Log.d("ViewModel", "onCleared")
    }
}