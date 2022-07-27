package com.marekpdev.shoppingapp.ui.paymentmethods

import com.marekpdev.shoppingapp.models.Address
import com.marekpdev.shoppingapp.models.PaymentCard
import com.marekpdev.shoppingapp.models.User
import com.marekpdev.shoppingapp.models.order.Order
import com.marekpdev.shoppingapp.models.order.PaymentMethod
import com.marekpdev.shoppingapp.mvi.Action
import com.marekpdev.shoppingapp.ui.login.LoginAction

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
sealed class PaymentMethodsAction : Action {

    object Loading: PaymentMethodsAction()

    data class RefreshData(val paymentMethods: List<PaymentMethod>): PaymentMethodsAction()

    data class PaymentMethodClicked(val paymentMethod: PaymentMethod): PaymentMethodsAction()

}