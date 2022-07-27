package com.marekpdev.shoppingapp.ui.paymentmethods

import android.util.Log
import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.repository.orders.OrdersRepository
import com.marekpdev.shoppingapp.repository.user.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
class PaymentMethodsNavigationMiddleware @Inject constructor() :
    Middleware<PaymentMethodsState, PaymentMethodsAction, PaymentMethodsCommand> {

    override suspend fun bind(
        coroutineScope: CoroutineScope,
        state: StateFlow<PaymentMethodsState>,
        requestAction: suspend (PaymentMethodsAction) -> Unit
    ) {

    }

    override suspend fun process(
        action: PaymentMethodsAction,
        currentState: PaymentMethodsState,
        requestAction: suspend (PaymentMethodsAction) -> Unit,
        requestCommand: suspend (PaymentMethodsCommand) -> Unit
    ) {
        when(action){
            is PaymentMethodsAction.PaymentMethodClicked -> requestCommand(PaymentMethodsCommand.GoToPaymentMethodDetails(action.paymentMethod))
        }
    }
}