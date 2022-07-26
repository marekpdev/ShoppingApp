package com.marekpdev.shoppingapp.repository.orders

import com.marekpdev.shoppingapp.models.order.Order
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
class OrdersRepositoryImpl @Inject constructor(): OrdersRepository{

    private val ordersPerUser = mapOf(
        1L to MutableStateFlow<List<Order>>(emptyList())
    )

    override suspend fun getOrders(userId: Long): StateFlow<List<Order>> {
        delay(1000L)
        return ordersPerUser[userId] ?: MutableStateFlow(emptyList())
    }
}