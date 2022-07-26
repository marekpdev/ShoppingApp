package com.marekpdev.shoppingapp.ui.orders

import com.marekpdev.shoppingapp.models.order.Order
import com.marekpdev.shoppingapp.mvi.State

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
data class OrdersState(
    val orders: List<Order>,
    val loading: Boolean
): State