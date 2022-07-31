package com.marekpdev.shoppingapp.ui.checkout

import com.marekpdev.shoppingapp.mvi.Command

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
sealed class CheckoutCommand: Command {

    object GoBackToBasketScreen: CheckoutCommand()
}