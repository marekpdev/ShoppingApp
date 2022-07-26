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
        // the actual time vary based on the current time
        return listOf(
            // TODAY
            Data.getOrder(userId, currentTime.minusDays(0).millis, 4),
            // YESTERDAY
            Data.getOrder(userId, currentTime.minusDays(1).millis, 2),
            Data.getOrder(userId, currentTime.minusDays(1).millis, 1),
            // THIS_WEEK
            Data.getOrder(userId, currentTime.minusDays(1).millis, 5),
            // LAST_WEEK
            Data.getOrder(userId, currentTime.minusDays(4).millis, 4),
            Data.getOrder(userId, currentTime.minusDays(5).millis, 7),
            Data.getOrder(userId, currentTime.minusDays(6).millis, 5),
            // LAST_MONTH
            Data.getOrder(userId, currentTime.minusDays(30).millis, 6),
            Data.getOrder(userId, currentTime.minusDays(40).millis, 2),
            Data.getOrder(userId, currentTime.minusDays(50).millis, 8),
            // LATER
            Data.getOrder(userId, currentTime.minusDays(100).millis, 2),
            Data.getOrder(userId, currentTime.minusDays(120).millis, 3),
        )
    }
}