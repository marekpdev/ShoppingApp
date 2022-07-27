package com.marekpdev.shoppingapp.ui.paymentmethods

import com.marekpdev.shoppingapp.models.order.PaymentMethod
import com.marekpdev.shoppingapp.mvi.Command

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
sealed class PaymentMethodsCommand: Command {

    object GoBackToAccountScreen: PaymentMethodsCommand()
    data class GoToPaymentMethodDetails(val paymentMethod: PaymentMethod): PaymentMethodsCommand()
}