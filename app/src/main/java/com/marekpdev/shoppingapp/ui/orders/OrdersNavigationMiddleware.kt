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

    }
}