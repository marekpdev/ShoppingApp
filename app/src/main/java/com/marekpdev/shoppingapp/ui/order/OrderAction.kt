package com.marekpdev.shoppingapp.ui.order

import com.marekpdev.shoppingapp.models.order.Order
import com.marekpdev.shoppingapp.mvi.Action

/**
 * Created by Marek Pszczolka on 05/07/2022.
 */
sealed class OrderAction: Action {

    data class FetchOrder(val orderId: Long): OrderAction()
    object Loading: OrderAction()
    data class OrderFetched(val order: Order): OrderAction()
    data class OrderError(val error: Throwable?): OrderAction()

}