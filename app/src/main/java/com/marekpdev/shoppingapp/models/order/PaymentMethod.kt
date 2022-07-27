package com.marekpdev.shoppingapp.models.order

/**
 * Created by Marek Pszczolka on 02/03/2022.
 */
data class PaymentMethod(
    val id: Long,
    val userId: Long,
    val label: String
)