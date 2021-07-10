package com.marekpdev.shoppingapp.models

/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
data class Order (
    val id: Long,
    val products: List<Product>,
    val totalCost: Double,
    val deliveryAddress: Address
)