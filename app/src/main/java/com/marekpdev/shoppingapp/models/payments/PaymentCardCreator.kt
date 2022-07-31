package com.marekpdev.shoppingapp.models.payments

/**
 * Created by Marek Pszczolka on 31/07/2022.
 */
data class PaymentCardCreator(
    val cardNumber: String,
    val cvc: String,
    val expiryDate: String
)