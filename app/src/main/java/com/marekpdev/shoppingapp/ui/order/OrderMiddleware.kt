package com.marekpdev.shoppingapp.ui.order

import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.repository.orders.OrdersRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 05/07/2022.
 */
class OrderMiddleware @Inject constructor(
    private val ordersRepository: OrdersRepository
): Middleware<OrderState, OrderAction, OrderCommand> {

    override suspend fun bind(
        coroutineScope: CoroutineScope,
        state: StateFlow<OrderState>,
        requestAction: suspend (OrderAction) -> Unit
    ) {

    }

    override suspend fun process(
        action: OrderAction,
        currentState: OrderState,
        requestAction: suspend (OrderAction) -> Unit,
        requestCommand: suspend (OrderCommand) -> Unit
    ) {
        when(action) {
            is OrderAction.FetchOrder -> onFetchOrder(action, currentState, requestAction, requestCommand)
        }
    }

    private suspend fun onFetchOrder(
        action: OrderAction.FetchOrder,
        currentState: OrderState,
        requestAction: suspend (OrderAction) -> Unit,
        requestCommand: suspend (OrderCommand) -> Unit
    ) {
        requestAction(OrderAction.Loading)
        val order = ordersRepository.getOrder(action.orderId)
        if(order != null) {
            requestAction(OrderAction.OrderFetched(order))
        } else {
            requestAction(OrderAction.OrderError(Exception("Order not found")))
        }
    }

}