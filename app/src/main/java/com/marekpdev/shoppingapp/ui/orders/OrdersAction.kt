package com.marekpdev.shoppingapp.ui.orders

import com.marekpdev.shoppingapp.models.order.Order
import com.marekpdev.shoppingapp.mvi.Action

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
sealed class OrdersAction : Action {

    object Loading: OrdersAction()

    data class RefreshData(val orders: List<Order>): OrdersAction()

    data class OrderClicked(val orderId: Long): OrdersAction()

}