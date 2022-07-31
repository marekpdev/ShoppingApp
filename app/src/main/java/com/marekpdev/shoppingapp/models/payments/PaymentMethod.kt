package com.marekpdev.shoppingapp.models.payments

/**
 * Created by Marek Pszczolka on 02/03/2022.
 */
interface PaymentMethod {
    val id: Long
    val userId: Long
    val label: String
}