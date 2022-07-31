package com.marekpdev.shoppingapp.models.payments

/**
 * Created by Marek Pszczolka on 28/02/2022.
 */
data class PaymentCard(
    override val id: Long,
    override val userId: Long,
    val hash: String, // secure payment card hash
    val provider: String,
    val last4Digits: String,
): PaymentMethod {

    override val label: String
        get() = "$provider **** **** **** $last4Digits"
}

