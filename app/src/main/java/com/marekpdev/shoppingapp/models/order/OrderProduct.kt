package com.marekpdev.shoppingapp.models.order

import com.marekpdev.shoppingapp.models.Color
import com.marekpdev.shoppingapp.models.Size

/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
data class OrderProduct(
    val id: Long,
    // properties from product
    val productId: Long,
    val name: String,
    val description: String,
    val price: Double,
    val currency: String,
    val images: List<String>,
    // order selections
    val selectedSize: Size?,
    val selectedColor: Color?
)