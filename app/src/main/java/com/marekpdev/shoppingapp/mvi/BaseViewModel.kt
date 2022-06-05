package com.marekpdev.shoppingapp.mvi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hadilq.liveevent.LiveEvent
import com.marekpdev.shoppingapp.extensions.asLiveData
import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * Created by Marek Pszczolka on 05/06/2022.
 */
open class BaseViewModel <S: State, A: Action, C: Command>(private val store: Store<S, A, C>): ViewModel() {

    // https://www.youtube.com/watch?v=Ls0uKLqNFz4&t=937s
    // in 20:15 there is entire binding strategy for rxjava for view model
    // but they are not using LiveData?

    private val _viewState = MutableLiveData<S>()
    val viewState = _viewState.asLiveData()

    private val _commands = LiveEvent<C>()
    val commands = _commands.asLiveData()

    private val compositeDisposable = CompositeDisposable()

    init {

    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    fun dispatch(action: A){
        store.dispatch(action)
    }

    fun onNewState(state: S) {
        _viewState.value = state
    }
}
