package com.marekpdev.shoppingapp.ui.paymentmethods

import com.marekpdev.shoppingapp.mvi.Reducer

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
class PaymentMethodsReducer: Reducer<PaymentMethodsState, PaymentMethodsAction> {

    override fun reduce(currentState: PaymentMethodsState, action: PaymentMethodsAction): PaymentMethodsState {
        return when (action){
            is PaymentMethodsAction.Loading -> {
                currentState.copy(loading = true)
            }
            is PaymentMethodsAction.RefreshData -> {
                currentState.copy(
                    paymentMethods = action.paymentMethods,
                    loading = false
                )
            }
            else -> {
                currentState
            }
        }
    }

}