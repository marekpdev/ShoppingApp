package com.marekpdev.shoppingapp.repository.orders

import com.marekpdev.shoppingapp.models.order.Order
import com.marekpdev.shoppingapp.models.order.OrderCreator
import com.marekpdev.shoppingapp.models.order.OrderProduct
import com.marekpdev.shoppingapp.models.order.OrderStatus
import com.marekpdev.shoppingapp.repository.Data
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import org.joda.time.DateTime
import java.util.concurrent.atomic.AtomicLong
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

    override suspend fun getOrder(orderId: Long): Order? = withContext(Dispatchers.IO) {
        delay(1000L) // TODO just for testing
        val order = ordersPerUser.flatMap { it.value.value }.find { it.id == orderId }
        order
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
            Data.getOrder(userId, currentTime.minusDays(2).millis, 5),
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

    private var orderId = AtomicLong(100000) // test value

    override suspend fun placeOrder(orderCreator: OrderCreator): Order {
        delay(2000L) //todo just for testing

        val orderProducts = orderCreator.products.map {
            OrderProduct(
                it.id,
                it.productId,
                it.name,
                it.description,
                it.price,
                it.currency,
                it.images,
                it.selectedSize,
                it.selectedColor
            )
        }

        val newOrder = Order(
            id = orderId.getAndIncrement(),
            userId = orderCreator.userId,
            products = orderProducts,
            totalCost = orderProducts.sumOf { it.price },
            deliveryAddress = orderCreator.deliveryAddress,
            paymentMethod = orderCreator.paymentMethod,
            createdAt = DateTime.now().millis,
            status = OrderStatus.IN_PROGRESS
        )

        val oldOrders = ordersPerUser[orderCreator.userId] ?: MutableStateFlow(emptyList())
        val newOrders = oldOrders.value.toMutableList().apply { add(newOrder) }
        ordersPerUser[orderCreator.userId]?.emit(newOrders)

        return newOrder
    }
}