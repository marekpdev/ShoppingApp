package com.marekpdev.shoppingapp.ui.orders

import com.marekpdev.shoppingapp.mvi.Command

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
sealed class OrdersCommand: Command {

    object GoBackToAccountScreen: OrdersCommand()
}