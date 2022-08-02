package com.marekpdev.shoppingapp.repository.orders

import com.marekpdev.shoppingapp.models.order.Order
import com.marekpdev.shoppingapp.models.order.OrderCreator
import kotlinx.coroutines.flow.StateFlow

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
interface OrdersRepository {

    suspend fun getOrders(userId: Long): StateFlow<List<Order>>

    suspend fun getOrder(orderId: Long): Order?

    suspend fun placeOrder(orderCreator: OrderCreator): Order

}