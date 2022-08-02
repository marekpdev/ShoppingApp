package com.marekpdev.shoppingapp.ui.paymentcard

import com.marekpdev.shoppingapp.models.payments.PaymentCard
import com.marekpdev.shoppingapp.mvi.State

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
data class PaymentCardState(
    val mode: Mode,
    val loading: Boolean,
    val paymentCardEdited: PaymentCard?,
    val cardNumber: String,
    val cvc: String,
    val expiryDate: String
): State

enum class Mode {
    ADD, UPDATE
}