package com.marekpdev.shoppingapp.ui.checkout

import com.marekpdev.shoppingapp.mvi.Middleware
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
class CheckoutNavigationMiddleware @Inject constructor() :
    Middleware<CheckoutState, CheckoutAction, CheckoutCommand> {

    override suspend fun bind(
        coroutineScope: CoroutineScope,
        state: StateFlow<CheckoutState>,
        requestAction: suspend (CheckoutAction) -> Unit
    ) {

    }

    override suspend fun process(
        action: CheckoutAction,
        currentState: CheckoutState,
        requestAction: suspend (CheckoutAction) -> Unit,
        requestCommand: suspend (CheckoutCommand) -> Unit
    ) {
        when(action){
            is CheckoutAction.SelectDeliveryAddressClicked -> {
                requestCommand(CheckoutCommand.ShowDeliveryAddressBottomSheet)
            }
            is CheckoutAction.SelectPaymentMethodClicked -> {
                requestCommand(CheckoutCommand.ShowPaymentMethodBottomSheet)
            }
            is CheckoutAction.SelectDeliveryAddress -> {
                requestCommand(CheckoutCommand.HideDeliveryAddressBottomSheet)
            }
            is CheckoutAction.SelectPaymentCard -> {
                requestCommand(CheckoutCommand.HidePaymentMethodBottomSheet)
            }
        }
    }
}