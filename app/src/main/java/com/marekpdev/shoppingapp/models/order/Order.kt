package com.marekpdev.shoppingapp.models.order

import com.marekpdev.shoppingapp.models.Address

/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
data class Order (
    val id: Long,
    val userId: Long,
    val products: List<OrderProduct>,
    val totalCost: Double,
    val deliveryAddress: Address,
    val paymentMethod: PaymentMethod,
    val createdAt: Long, // timestamp in ms
    val status: OrderStatus
)