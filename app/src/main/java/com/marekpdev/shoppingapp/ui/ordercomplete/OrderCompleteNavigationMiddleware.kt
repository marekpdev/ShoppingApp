package com.marekpdev.shoppingapp.ui.ordercomplete

import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.repository.orders.OrdersRepository
import com.marekpdev.shoppingapp.repository.user.UserRepository
import com.marekpdev.shoppingapp.ui.orders.OrdersAction
import com.marekpdev.shoppingapp.ui.orders.OrdersCommand
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
class OrderCompleteNavigationMiddleware @Inject constructor() :
    Middleware<OrderCompleteState, OrderCompleteAction, OrderCompleteCommand> {

    override suspend fun bind(
        coroutineScope: CoroutineScope,
        state: StateFlow<OrderCompleteState>,
        requestAction: suspend (OrderCompleteAction) -> Unit
    ) {

    }

    override suspend fun process(
        action: OrderCompleteAction,
        currentState: OrderCompleteState,
        requestAction: suspend (OrderCompleteAction) -> Unit,
        requestCommand: suspend (OrderCompleteCommand) -> Unit
    ) {
        when(action){
            is OrderCompleteAction.SeeOrderDetailsClicked -> { requestCommand(OrderCompleteCommand.GoToOrderDetails(currentState.order?.id ?: 0)) }
        }
    }
}