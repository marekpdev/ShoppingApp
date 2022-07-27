package com.marekpdev.shoppingapp.ui.home

import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.mvi.Store

/**
 * Created by Marek Pszczolka on 20/07/2022.
 */
class HomeStore (
    initialState: HomeState,
    middlewares: List<Middleware<HomeState, HomeAction, HomeCommand>>,
    reducer: HomeReducer
) : Store<HomeState, HomeAction, HomeCommand>(initialState, middlewares, reducer)