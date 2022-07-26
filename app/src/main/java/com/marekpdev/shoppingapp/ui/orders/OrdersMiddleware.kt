package com.marekpdev.shoppingapp.ui.orders

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
class OrdersMiddleware @Inject constructor(private val userRepository: UserRepository,
                                           private val ordersRepository: OrdersRepository
    ) :
    Middleware<OrdersState, OrdersAction, OrdersCommand> {

    override suspend fun bind(
        coroutineScope: CoroutineScope,
        state: StateFlow<OrdersState>,
        requestAction: suspend (OrdersAction) -> Unit
    ) {
        coroutineScope.launch {
            // todo figure out a better way to chain these flows
            // userRepository.getUser() & ordersRepository.getOrders(it.id)
            userRepository.getUser()
                .collectLatest { user ->
                    user?.let {
                        Log.d("FEO33", "Orders middleware id ${it.id}")
                        ordersRepository.getOrders(it.id).collectLatest { orders ->
                            Log.d("FEO33", "get latest middleware ${orders.size}")
                            requestAction(OrdersAction.RefreshData(orders))
                        }
                    }
                }
        }
    }

    override suspend fun process(
        action: OrdersAction,
        currentState: OrdersState,
        requestAction: suspend (OrdersAction) -> Unit,
        requestCommand: suspend (OrdersCommand) -> Unit
    ) {

    }
}