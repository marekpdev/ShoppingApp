package com.marekpdev.shoppingapp.ui.checkout

import com.marekpdev.shoppingapp.mvi.Reducer

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
class CheckoutReducer: Reducer<CheckoutState, CheckoutAction> {

    override fun reduce(currentState: CheckoutState, action: CheckoutAction): CheckoutState {
        return when (action){
            is CheckoutAction.LoadingDeliveryAddresses -> {
                currentState.copy(loadingDeliveryAddresses = true)
            }
            is CheckoutAction.LoadingPaymentMethods -> {
                currentState.copy(loadingPaymentMethods = true)
            }
            is CheckoutAction.RefreshDeliveryAddresses -> {
                currentState.copy(deliveryAddresses = action.addresses, loadingDeliveryAddresses = false)
            }
            is CheckoutAction.RefreshPaymentMethods -> {
                currentState.copy(paymentMethods = action.paymentMethods, loadingPaymentMethods = false)
            }
            is CheckoutAction.SelectDeliveryAddress -> {
                currentState.copy(selectedDeliveryAddress = action.address)
            }
            is CheckoutAction.SelectPaymentCard -> {
                currentState.copy(selectedPaymentMethod = action.paymentCard)
            }
            else -> {
                currentState
            }
        }
    }

}