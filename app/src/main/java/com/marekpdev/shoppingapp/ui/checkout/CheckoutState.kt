package com.marekpdev.shoppingapp.ui.checkout

import com.marekpdev.shoppingapp.models.Address
import com.marekpdev.shoppingapp.models.order.Order
import com.marekpdev.shoppingapp.models.payments.PaymentMethod
import com.marekpdev.shoppingapp.mvi.State

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
data class CheckoutState(
    val deliveryAddress: Address?,
    val paymentMethod: PaymentMethod?,
    val totalToPay: Double,
    val loading: Boolean
): State