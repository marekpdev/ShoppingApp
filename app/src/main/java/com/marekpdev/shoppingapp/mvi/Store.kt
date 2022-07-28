package com.marekpdev.shoppingapp.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

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
    // UPDATE: when in AddressViewModel when we are editing item we are firing two actions at the same time
    // so we need to change the replay to 2 - but preferably we want to be able to fire even more actions
    // so I just set the value replay to 10. Need to fix it later on so we don't need to use hacks to process
    // all needed actions
    private val _actions = MutableSharedFlow<A>(replay = 10, extraBufferCapacity = 20)
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

                reducer.reduce(currentState, action)
            }
                .distinctUntilChanged()
                .onEach { newState ->
                    _state.emit(newState)
                }
                .collect()
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

    open fun canHandleBackPressed(): Boolean = false

}