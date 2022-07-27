package com.marekpdev.shoppingapp.ui.order

import com.marekpdev.shoppingapp.mvi.Command

/**
 * Created by Marek Pszczolka on 05/07/2022.
 */
sealed class OrderCommand: Command {

    object TestOrderCommand: OrderCommand()

}