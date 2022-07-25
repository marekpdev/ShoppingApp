package com.marekpdev.shoppingapp.ui.basket

import com.marekpdev.shoppingapp.mvi.Command

/**
 * Created by Marek Pszczolka on 25/07/2022.
 */
sealed class BasketCommand: Command {

    object ContinueCheckout: BasketCommand()

    data class EditBasketProduct(val basketProductId: Long): BasketCommand()

}