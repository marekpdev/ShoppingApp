package com.marekpdev.shoppingapp.ui.ordercomplete

import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.repository.orders.OrdersRepository
import com.marekpdev.shoppingapp.repository.user.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
class OrderCompleteMiddleware @Inject constructor(private val userRepository: UserRepository,
                                                  private val ordersRepository: OrdersRepository
    ) :
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
        when(action) {
            is OrderCompleteAction.FetchOrder -> onFetchOrder(action, currentState, requestAction, requestCommand)
        }
    }

    private suspend fun onFetchOrder(
        action: OrderCompleteAction.FetchOrder,
        currentState: OrderCompleteState,
        requestAction: suspend (OrderCompleteAction) -> Unit,
        requestCommand: suspend (OrderCompleteCommand) -> Unit
    ) {
        requestAction(OrderCompleteAction.Loading)
        val order = ordersRepository.getOrder(action.orderId)
        if(order != null) {
            requestAction(OrderCompleteAction.OrderFetched(order))
        } else {
            requestAction(OrderCompleteAction.OrderError(Exception("Order not found")))
        }
    }
}