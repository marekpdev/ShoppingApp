package com.marekpdev.shoppingapp.ui.orders

import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.mvi.Store

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
class OrdersStore (
    initialState: OrdersState,
    middlewares: List<Middleware<OrdersState, OrdersAction, OrdersCommand>>,
    reducer: OrdersReducer
) : Store<OrdersState, OrdersAction, OrdersCommand>(initialState, middlewares, reducer)