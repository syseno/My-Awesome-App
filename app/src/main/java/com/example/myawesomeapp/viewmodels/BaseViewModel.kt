package com.example.myawesomeapp.viewmodels

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myawesomeapp.tools.ProgressState
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel(), LifecycleObserver {

    protected val disposables = CompositeDisposable()

    protected var _progressState: MutableLiveData<ProgressState> = MutableLiveData()
    val progressState: LiveData<ProgressState>
        get() = _progressState

    private fun clearDisposables() {
        disposables.clear()
    }

    override fun onCleared() {
        clearDisposables()
        super.onCleared()
    }
}