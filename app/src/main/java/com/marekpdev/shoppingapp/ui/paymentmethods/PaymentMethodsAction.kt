package com.marekpdev.shoppingapp.ui.paymentmethods

import com.marekpdev.shoppingapp.models.order.PaymentMethod
import com.marekpdev.shoppingapp.mvi.Action

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
sealed class PaymentMethodsAction : Action {

    object Loading: PaymentMethodsAction()

    data class RefreshData(val paymentMethods: List<PaymentMethod>): PaymentMethodsAction()

    data class PaymentMethodClicked(val paymentMethod: PaymentMethod): PaymentMethodsAction()

}