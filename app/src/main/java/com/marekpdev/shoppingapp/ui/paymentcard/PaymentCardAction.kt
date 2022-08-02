package com.marekpdev.shoppingapp.ui.paymentcard

import com.marekpdev.shoppingapp.models.payments.PaymentCard
import com.marekpdev.shoppingapp.mvi.Action

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
sealed class PaymentCardAction : Action {

    data class OnContentChanged(
        val cardNumber: String,
        val cvc: String,
        val expiryDate: String
    ): PaymentCardAction()

    data class FetchPaymentCard(val paymentCardId: Long): PaymentCardAction()
    data class Initialize(val mode: Mode): PaymentCardAction()
    object Loading: PaymentCardAction()

    data class PaymentCardFetched(val paymentCard: PaymentCard): PaymentCardAction()

    object AddPaymentCard: PaymentCardAction()
    object UpdatePaymentCard: PaymentCardAction()

    object PaymentCardAdded: PaymentCardAction()
    object PaymentCardUpdated: PaymentCardAction()

}