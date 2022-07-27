package com.marekpdev.shoppingapp.ui.order

import com.marekpdev.shoppingapp.models.Color
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.models.Size
import com.marekpdev.shoppingapp.models.order.Order
import com.marekpdev.shoppingapp.mvi.State

/**
 * Created by Marek Pszczolka on 05/07/2022.
 */
data class OrderState (
    val order: Order?,
    val loading: Boolean
): State