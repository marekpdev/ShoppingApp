package com.marekpdev.shoppingapp.ui.ordercomplete

import com.marekpdev.shoppingapp.models.order.Order
import com.marekpdev.shoppingapp.mvi.Action

/**
 * Created by Marek Pszczolka on 25/07/2022.
 */
sealed class OrderCompleteAction: Action {

    data class FetchOrder(val orderId: Long): OrderCompleteAction()
    object Loading: OrderCompleteAction()
    data class OrderFetched(val order: Order): OrderCompleteAction()
    data class OrderError(val error: Throwable?): OrderCompleteAction()

    object SeeOrderDetailsClicked: OrderCompleteAction()
}