package com.marekpdev.shoppingapp.ui.search

import com.jakewharton.rxrelay3.BehaviorRelay
import com.jakewharton.rxrelay3.PublishRelay
import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.mvi.Store
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

/**
 * Created by Marek Pszczolka on 04/06/2022.
 */
class SearchStore(initialState: SearchViewState,
                  middlewares: List<Middleware<SearchViewState, SearchAction>>,
                  reducer: SearchReducer)
    : Store<SearchViewState, SearchAction>(initialState, middlewares, reducer) {

}