package com.marekpdev.shoppingapp.models

/**
 * Created by Marek Pszczolka on 28/02/2022.
 */
data class PaymentCard(
    val id: Int,
    val hash: String, // secure payment card hash
    val provider: String,
    val last4Digits: String,
)