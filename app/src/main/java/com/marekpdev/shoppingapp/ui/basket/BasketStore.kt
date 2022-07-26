package com.marekpdev.shoppingapp.ui.basket

import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.mvi.Store

/**
 * Created by Marek Pszczolka on 25/07/2022.
 */
class BasketStore (
    initialState: BasketState,
    middlewares: List<Middleware<BasketState, BasketAction, BasketCommand>>,
    reducer: BasketReducer
) : Store<BasketState, BasketAction, BasketCommand>(initialState, middlewares, reducer)