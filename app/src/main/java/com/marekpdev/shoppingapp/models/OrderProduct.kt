package com.marekpdev.shoppingapp.models

/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
data class OrderProduct(
    val product: Product,
    val size: Size?,
    val color: Color?
)