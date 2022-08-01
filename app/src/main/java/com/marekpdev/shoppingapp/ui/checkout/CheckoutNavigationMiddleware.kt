package com.marekpdev.shoppingapp.ui.checkout

import android.util.Log
import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.repository.basket.BasketRepository
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