package com.marekpdev.shoppingapp.ui.checkout

import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.mvi.Store

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
class CheckoutStore (
    initialState: CheckoutState,
    middlewares: List<Middleware<CheckoutState, CheckoutAction, CheckoutCommand>>,
    reducer: CheckoutReducer
) : Store<CheckoutState, CheckoutAction, CheckoutCommand>(initialState, middlewares, reducer)