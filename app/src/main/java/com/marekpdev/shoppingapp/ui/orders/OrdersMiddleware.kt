package com.marekpdev.shoppingapp.ui.orders

import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.repository.orders.OrdersRepository
import com.marekpdev.shoppingapp.repository.user.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
class OrdersMiddleware @Inject constructor(private val userRepository: UserRepository,
                                           private val ordersRepository: OrdersRepository
    ) : Middleware<OrdersState, OrdersAction, OrdersCommand> {

    override suspend fun bind(
        coroutineScope: CoroutineScope,
        state: StateFlow<OrdersState>,
        requestAction: suspend (OrdersAction) -> Unit
    ) {
        userRepository.getUser()
            .filterNotNull()
            .onEach { requestAction(OrdersAction.Loading) }
            .flatMapLatest { user -> ordersRepository.getOrders(user.id) }
            .onEach { orders -> requestAction(OrdersAction.RefreshData(orders)) }
            .launchIn(coroutineScope)
    }

    override suspend fun process(
        action: OrdersAction,
        currentState: OrdersState,
        requestAction: suspend (OrdersAction) -> Unit,
        requestCommand: suspend (OrdersCommand) -> Unit
    ) {

    }
}