package com.marekpdev.shoppingapp.ui.ordercomplete

import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.mvi.Store

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
class OrderCompleteStore (
    initialState: OrderCompleteState,
    middlewares: List<Middleware<OrderCompleteState, OrderCompleteAction, OrderCompleteCommand>>,
    reducer: OrderCompleteReducer
) : Store<OrderCompleteState, OrderCompleteAction, OrderCompleteCommand>(initialState, middlewares, reducer)