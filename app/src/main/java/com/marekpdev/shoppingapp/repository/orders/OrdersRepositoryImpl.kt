package com.marekpdev.shoppingapp.repository.orders

import com.marekpdev.shoppingapp.models.order.Order
import com.marekpdev.shoppingapp.repository.Data
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.joda.time.DateTime
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
class OrdersRepositoryImpl @Inject constructor(): OrdersRepository{

    private val ordersPerUser = mapOf(
        1L to MutableStateFlow(getTestOrders(1L))
    )

    override suspend fun getOrders(userId: Long): StateFlow<List<Order>> {
        delay(1000L)
        return ordersPerUser[userId] ?: MutableStateFlow(emptyList())
    }

    private fun getTestOrders(userId: Long): List<Order> {
        val currentTime = DateTime.now()
        return listOf(
            Data.getOrder(userId, currentTime.minusDays(1).millis, 4),
            Data.getOrder(userId, currentTime.minusDays(1).millis, 5),
            Data.getOrder(userId, currentTime.minusDays(2).millis, 2),
            Data.getOrder(userId, currentTime.minusDays(2).millis, 3),
            Data.getOrder(userId, currentTime.minusDays(4).millis, 7),
            Data.getOrder(userId, currentTime.minusDays(8).millis, 1),
            Data.getOrder(userId, currentTime.minusDays(9).millis, 3),
            Data.getOrder(userId, currentTime.minusDays(16).millis, 8),
        )
    }
}