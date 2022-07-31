package com.marekpdev.shoppingapp.ui.paymentcard

import com.marekpdev.shoppingapp.mvi.Middleware
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
class PaymentCardNavigationMiddleware @Inject constructor() :
    Middleware<PaymentCardState, PaymentCardAction, PaymentCardCommand> {

    override suspend fun bind(
        coroutineScope: CoroutineScope,
        state: StateFlow<PaymentCardState>,
        requestAction: suspend (PaymentCardAction) -> Unit
    ) {

    }

    override suspend fun process(
        action: PaymentCardAction,
        currentState: PaymentCardState,
        requestAction: suspend (PaymentCardAction) -> Unit,
        requestCommand: suspend (PaymentCardCommand) -> Unit
    ) {
        when(action){
            is PaymentCardAction.PaymentCardAdded -> requestCommand(PaymentCardCommand.GoBackToPaymentMethodsScreen)
            is PaymentCardAction.PaymentCardUpdated -> requestCommand(PaymentCardCommand.GoBackToPaymentMethodsScreen)
        }
    }
}