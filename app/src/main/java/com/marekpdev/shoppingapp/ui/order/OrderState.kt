package com.marekpdev.shoppingapp.ui.order

import com.marekpdev.shoppingapp.models.order.Order
import com.marekpdev.shoppingapp.mvi.State

/**
 * Created by Marek Pszczolka on 05/07/2022.
 */
data class OrderState (
    val order: Order?,
    val loading: Boolean
): State