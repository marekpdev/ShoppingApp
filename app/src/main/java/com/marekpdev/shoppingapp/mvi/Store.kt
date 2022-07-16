package com.marekpdev.shoppingapp.mvi

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

/**
 * Created by Marek Pszczolka on 04/06/2022.
 */
open class Store <S: State, A: Action, C: Command> (
    initialState: S,
    private val middlewares: List<Middleware<S, A, C>>,
    private val reducer: Reducer<S, A>
) {

    private val _actions = MutableSharedFlow<A>()
    val actions = _actions.asSharedFlow()

    private val _state = MutableStateFlow<S>(initialState)
    val state = _state.asStateFlow()

    private val _commands = MutableSharedFlow<C>()
    val commands = _commands.asSharedFlow()

    // use DI
    // https://medium.com/androiddevelopers/create-an-application-coroutinescope-using-hilt-dd444e721528
    // https://developer.android.com/kotlin/coroutines/coroutines-best-practices#create-coroutines-data-layer
    private val coroutineScope = CoroutineScope(Dispatchers.Main) // TODO use DI

    init {

        middlewares.forEach { middleware ->
            coroutineScope.launch {
                middleware.bind(coroutineScope, state, _actions::emit)
            }
        }

        coroutineScope.launch {
            actions.map { action ->
                val currentState = state.value
                middlewares.forEach { middleware ->
                    middleware.process(
                        action,
                        currentState,
                        _actions::emit,
                        _commands::emit
                    )
                }
                reducer.reduce(currentState, action)
            }
                .distinctUntilChanged()
                .collectLatest { newState ->
                    _state.emit(newState)
                }
        }
    }

    /**
     * We can receive actions from both UI and external sources (such as middleware)
     */
    suspend fun dispatch(action: A){
        _actions.emit(action)
    }

    /**
     * We cannot receive actions from UI but only from external sources (such as middleware)
     */
    private suspend fun dispatch(command: C){
        _commands.emit(command)
    }

}