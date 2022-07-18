package com.marekpdev.shoppingapp.mvi

import android.util.Log
import com.marekpdev.shoppingapp.ui.search.SearchState
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

    // TODO 1
    // there was an issue that when we are processing action (going through all middlewares etc)
    // then when in the meantime we request new action (by requestAction(action)) then the remaining
    // workflow (going through all other middlewares and then reduce) is suspended and then we the
    // actions workflow has been blocked
    // see more info here
    // https://itnext.io/mutablesharedflow-is-kind-of-complicated-61af68011eae
    // "Emitters and Collectors are independent of each other.
    // Emitters try to emit an event to the MutableSharedFlow and
    // they don’t necessarily wait for Collectors to collect them."
    // for the moment we set buffer capacity to some value but that is probably not ideal solution -
    // might need to investigate that later
    // TODO 2
    // also there is an issue for ProductFragment workflow
    // 1) Store is being initialized ( init{} )
    // 2) ProductViewModel is being initialized (init {}) and sends FetchProduct action
    // 3) Store's coroutineScope launch is being executed and only then actions are being properly read one by one
    // 4) because we sent FetchProduct before the coroutine has been launched and because actions is hot observable
    // then we don't receive the action
    // To make a quick fix for that I set replay = 1 but might need to look into a better solution
    private val _actions = MutableSharedFlow<A>(replay = 1, extraBufferCapacity = 20)
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


        Log.d("FEO900", "Store INIT")
        coroutineScope.launch {
            Log.d("FEO900", "Store INIT 2")
            actions.map { action ->
                Log.d("FEO900", "Store actions.map $action")
                val currentState = state.value
                middlewares.forEach { middleware ->
                    Log.d("FEO800", "${middlewares.size} - middleware $middleware -> $action")
                    // we want to do things in parallel here so we can call reducer.reduce immediately
                    // and not block the workflow here
                    launch {
                        middleware.process(
                            action,
                            currentState,
                            _actions::emit,
                            _commands::emit
                        )
                    }
                }
                Log.d("FEO900", "Store actions.reduce $action")
                reducer.reduce(currentState, action)
            }
                .distinctUntilChanged()
                .onEach { newState ->
                    Log.d("FEO900", "collect $newState")
                    if(newState is SearchState){
                        Log.d("FEO900", "new state ${newState.sortType}")
                    }

                    _state.emit(newState)
                }
                .collect()
        }
    }

    /**
     * We can receive actions from both UI and external sources (such as middleware)
     */
    suspend fun dispatch(action: A){
        Log.d("FEO900", "Store dispatch action")
        _actions.emit(action)
    }

    /**
     * We cannot receive actions from UI but only from external sources (such as middleware)
     */
    private suspend fun dispatch(command: C){
        Log.d("FEO900", "dispatch command $command")
        _commands.emit(command)
    }

    open fun canHandleBackPressed(): Boolean = false

}