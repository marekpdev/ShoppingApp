package com.marekpdev.shoppingapp.ui.checkout

import com.marekpdev.shoppingapp.models.Address
import com.marekpdev.shoppingapp.models.order.Order
import com.marekpdev.shoppingapp.models.payments.PaymentCard
import com.marekpdev.shoppingapp.mvi.Action

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
sealed class CheckoutAction : Action {

    object Loading: CheckoutAction()

    object SelectDeliveryAddressClicked: CheckoutAction()
    object SelectPaymentMethodClicked: CheckoutAction()

    data class SelectDeliveryAddress(val address: Address): CheckoutAction()
    data class SelectPaymentCard(val paymentCard: PaymentCard): CheckoutAction()
}