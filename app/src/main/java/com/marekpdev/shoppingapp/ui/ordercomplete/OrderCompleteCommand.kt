package com.marekpdev.shoppingapp.ui.ordercomplete

import com.marekpdev.shoppingapp.mvi.Command
import com.marekpdev.shoppingapp.ui.orders.OrdersCommand

/**
 * Created by Marek Pszczolka on 25/07/2022.
 */
sealed class OrderCompleteCommand: Command {

    object GoBackToBasket: OrderCompleteCommand()
    data class GoToOrderDetails(val orderId: Long): OrderCompleteCommand()

}