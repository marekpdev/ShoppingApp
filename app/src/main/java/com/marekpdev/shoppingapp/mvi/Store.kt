package com.marekpdev.shoppingapp.mvi

import androidx.lifecycle.LiveDataReactiveStreams
import com.jakewharton.rxrelay3.BehaviorRelay
import com.jakewharton.rxrelay3.PublishRelay
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

    private val state = BehaviorRelay.createDefault(initialState)
    private val actions = PublishRelay.create<A>()

    val s2 = LiveDataReactiveStreams.fromPublisher(s)
//
//    private var currentState = initialState
//
//    fun wire(): Disposable {
//        val disposable = CompositeDisposable()
//
//        // todo need to change to
//        // disposable += actions ...
//        disposable.add(
//            actions.withLatestFrom(state) { action, state ->
//                for(middleware in middlewares){
//                    middleware.dispatch(action)
//                }
//
//                reducer.reduce(currentState, action)
//            }
//                .distinctUntilChanged()
//                .subscribe(state::accept)
//        )
//
//        return disposable
//    }
//
//    fun dispatch(action: SearchAction){
//        actions.accept(action)
//    }



    // this is for coroutines
    //private val _state = MutableStateFlow(initialState)
    //val state: StateFlow<S> = _state

    fun dispatch(action: A){
//        val currentState = _state.value
//
//        middlewares.forEach { it.process(action, currentState, this)}
//
//        val newState = reducer.reduce(currentState, action)
//        _state.value = newState
    }

}