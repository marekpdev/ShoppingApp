package com.marekpdev.shoppingapp.ui.paymentmethods

import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.mvi.Store

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
class PaymentMethodsStore (
    initialState: PaymentMethodsState,
    middlewares: List<Middleware<PaymentMethodsState, PaymentMethodsAction, PaymentMethodsCommand>>,
    reducer: PaymentMethodsReducer
) : Store<PaymentMethodsState, PaymentMethodsAction, PaymentMethodsCommand>(initialState, middlewares, reducer)