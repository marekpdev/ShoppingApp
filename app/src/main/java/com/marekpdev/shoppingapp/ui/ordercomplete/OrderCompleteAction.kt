package com.marekpdev.shoppingapp.ui.ordercomplete

import com.marekpdev.shoppingapp.models.BasketProduct
import com.marekpdev.shoppingapp.models.Color
import com.marekpdev.shoppingapp.models.Size
import com.marekpdev.shoppingapp.models.order.Order
import com.marekpdev.shoppingapp.mvi.Action
import com.marekpdev.shoppingapp.ui.order.OrderAction

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