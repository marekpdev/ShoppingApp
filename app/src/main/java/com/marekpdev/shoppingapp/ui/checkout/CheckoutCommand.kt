package com.marekpdev.shoppingapp.ui.checkout

import com.marekpdev.shoppingapp.mvi.Command
import com.marekpdev.shoppingapp.ui.search.SearchCommand

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
sealed class CheckoutCommand: Command {

    object GoBackToBasketScreen: CheckoutCommand()

    object ShowDeliveryAddressBottomSheet : CheckoutCommand()
    object HideDeliveryAddressBottomSheet : CheckoutCommand()
    object ShowPaymentMethodBottomSheet : CheckoutCommand()
    object HidePaymentMethodBottomSheet : CheckoutCommand()

    data class GoToOrderCompleteScreen(val orderId: Long): CheckoutCommand()
}