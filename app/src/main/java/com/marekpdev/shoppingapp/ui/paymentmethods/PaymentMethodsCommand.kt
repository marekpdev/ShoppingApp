package com.marekpdev.shoppingapp.ui.paymentmethods

import com.marekpdev.shoppingapp.models.Address
import com.marekpdev.shoppingapp.models.payments.PaymentCard
import com.marekpdev.shoppingapp.models.payments.PaymentMethod
import com.marekpdev.shoppingapp.mvi.Command
import com.marekpdev.shoppingapp.ui.addresses.AddressesCommand

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
sealed class PaymentMethodsCommand: Command {

    object GoBackToAccountScreen: PaymentMethodsCommand()
    data class GoToPaymentMethodDetails(val paymentMethod: PaymentMethod): PaymentMethodsCommand()

    data class GoToPaymentCardDetails(val paymentCard: PaymentCard): PaymentMethodsCommand()
    object GoToAddPaymentCardScreen: PaymentMethodsCommand()
}