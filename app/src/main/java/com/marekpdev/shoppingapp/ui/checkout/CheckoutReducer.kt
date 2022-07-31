package com.marekpdev.shoppingapp.ui.checkout

import com.marekpdev.shoppingapp.mvi.Reducer

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
class CheckoutReducer: Reducer<CheckoutState, CheckoutAction> {

    override fun reduce(currentState: CheckoutState, action: CheckoutAction): CheckoutState {
        return when (action){
            is CheckoutAction.Loading -> {
                currentState.copy(loading = true)
            }
            else -> {
                currentState
            }
        }
    }

}