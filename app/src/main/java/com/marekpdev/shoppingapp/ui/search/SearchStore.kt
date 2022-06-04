package com.marekpdev.shoppingapp.ui.search

import com.jakewharton.rxrelay3.BehaviorRelay
import com.jakewharton.rxrelay3.PublishRelay
import com.marekpdev.shoppingapp.mvi.Store
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

/**
 * Created by Marek Pszczolka on 04/06/2022.
 */
class SearchStore(initialState: SearchViewState,
                  middlewares: List<Middleware>,
                  reducer: SearchReducer)
    : Store<SearchViewState, SearchAction>(initialState, middlewares, reducer) {



//    private val state = BehaviorRelay.createDefault(initialState)
//    private val actions = PublishRelay.create<SearchAction>()
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

}