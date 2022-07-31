package com.marekpdev.shoppingapp.ui.paymentcard

import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.mvi.Store

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
class PaymentCardStore (
    initialState: PaymentCardState,
    middlewares: List<Middleware<PaymentCardState, PaymentCardAction, PaymentCardCommand>>,
    reducer: PaymentCardReducer
) : Store<PaymentCardState, PaymentCardAction, PaymentCardCommand>(initialState, middlewares, reducer)