package com.marekpdev.shoppingapp.mvi

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Created by Marek Pszczolka on 04/06/2022.
 */
open class Store <S: ViewState, A: Action> (
    private val initialState: S,
    private val middlewares: List<Middleware<S, A>>,
    private val reducer: Reducer<S, A>
) {

    // this is for coroutines
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state

    fun dispatch(action: A){
        val currentState = _state.value
        val newState = reducer.reduce(currentState, action)
        _state.value = newState
    }

}