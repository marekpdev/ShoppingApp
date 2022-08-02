package com.marekpdev.shoppingapp.ui.ordercomplete

import com.marekpdev.shoppingapp.models.BasketProduct
import com.marekpdev.shoppingapp.models.order.Order
import com.marekpdev.shoppingapp.mvi.State

/**
 * Created by Marek Pszczolka on 25/07/2022.
 */
data class OrderCompleteState(
    val order: Order?,
    val loading: Boolean
) : State