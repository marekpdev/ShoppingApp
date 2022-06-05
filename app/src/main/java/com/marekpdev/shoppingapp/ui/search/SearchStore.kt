package com.marekpdev.shoppingapp.ui.search

import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.mvi.Store

/**
 * Created by Marek Pszczolka on 04/06/2022.
 */

class SearchStore(initialState: SearchState,
                  middlewares: List<Middleware<SearchState, SearchAction, SearchCommand>>,
                  reducer: SearchReducer) : Store<SearchState, SearchAction, SearchCommand>(initialState, middlewares, reducer) {

    companion object {

        // TODO need to use DI
        val INSTANCE by lazy {
            SearchStore(
                SearchState("", false, "", emptyList()),
                listOf(SearchMiddleware(), SearchNavigationMiddleware()),
                SearchReducer()
            )
        }

    }

}