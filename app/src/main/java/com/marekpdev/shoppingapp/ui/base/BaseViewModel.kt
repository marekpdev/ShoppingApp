package com.marekpdev.shoppingapp.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hadilq.liveevent.LiveEvent
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 07/05/2022.
 */
open class BaseViewModelOld @Inject constructor(): ViewModel() {

    private val inProgress = LiveEvent<Boolean>()

    protected val compositeDisposable = CompositeDisposable()
    protected val error = LiveEvent<Throwable>()

    fun observeProgress(): LiveData<Boolean> = inProgress
    fun observeErrors(): LiveData<Throwable> = error

    fun startProgress() = inProgress.postValue(true)
    fun stopProgress() = inProgress.postValue(false)

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    protected fun Disposable.addToDisposable() {
        compositeDisposable.add(this)
    }

}