package com.marekpdev.shoppingapp.ui.order

import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.mvi.Store

/**
 * Created by Marek Pszczolka on 05/07/2022.
 */
class OrderStore (
    initialState: OrderState,
    middlewares: List<Middleware<OrderState, OrderAction, OrderCommand>>,
    reducer: OrderReducer
) : Store<OrderState, OrderAction, OrderCommand>(initialState, middlewares, reducer)