package com.marekpdev.shoppingapp.ui.paymentcard

import com.marekpdev.shoppingapp.mvi.Reducer

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
class PaymentCardReducer: Reducer<PaymentCardState, PaymentCardAction> {

    override fun reduce(currentState: PaymentCardState, action: PaymentCardAction): PaymentCardState {
        return when (action){
            is PaymentCardAction.Initialize -> {
                currentState.copy(
                    mode = action.mode,
                    cardNumber = "",
                    cvc = "",
                    expiryDate = ""
                )
            }
            is PaymentCardAction.OnContentChanged -> {
                currentState.copy(
                    cardNumber = action.cardNumber,
                    cvc = action.cvc,
                    expiryDate = action.expiryDate
                )
            }
            is PaymentCardAction.Loading -> {
                currentState.copy(loading = true)
            }
            is PaymentCardAction.PaymentCardFetched -> {
                val paymentCard = action.paymentCard
                currentState.copy(
                    loading = false,
                    paymentCardEdited = paymentCard,
                    cardNumber = "",
                    cvc = "",
                    expiryDate = ""
                )
            }
            is PaymentCardAction.PaymentCardAdded -> {
                currentState.copy(
                    loading = false
                )
            }
            is PaymentCardAction.PaymentCardUpdated -> {
                currentState.copy(
                    loading = false
                )
            }
            else -> {
                currentState
            }
        }
    }

}