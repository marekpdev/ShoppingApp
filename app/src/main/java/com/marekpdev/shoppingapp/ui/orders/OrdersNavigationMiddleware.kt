package com.marekpdev.shoppingapp.ui.orders

import com.marekpdev.shoppingapp.mvi.Middleware
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
class OrdersNavigationMiddleware @Inject constructor() :
    Middleware<OrdersState, OrdersAction, OrdersCommand> {

    override suspend fun bind(
        coroutineScope: CoroutineScope,
        state: StateFlow<OrdersState>,
        requestAction: suspend (OrdersAction) -> Unit
    ) {

    }

    override suspend fun process(
        action: OrdersAction,
        currentState: OrdersState,
        requestAction: suspend (OrdersAction) -> Unit,
        requestCommand: suspend (OrdersCommand) -> Unit
    ) {
        when(action){
            is OrdersAction.OrderClicked -> requestCommand(OrdersCommand.GoToOrderDetails(action.orderId))
        }
    }
}