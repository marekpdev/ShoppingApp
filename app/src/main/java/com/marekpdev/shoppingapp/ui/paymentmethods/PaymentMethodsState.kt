package com.marekpdev.shoppingapp.ui.paymentmethods

import com.marekpdev.shoppingapp.models.Address
import com.marekpdev.shoppingapp.models.order.Order
import com.marekpdev.shoppingapp.models.order.PaymentMethod
import com.marekpdev.shoppingapp.mvi.State

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
data class PaymentMethodsState(
    val paymentMethods: List<PaymentMethod>,
    val loading: Boolean
): State