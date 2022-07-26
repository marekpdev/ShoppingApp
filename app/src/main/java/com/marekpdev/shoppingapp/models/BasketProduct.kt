package com.marekpdev.shoppingapp.models

/**
 * Created by Marek Pszczolka on 25/07/2022.
 */
data class BasketProduct (
    val id: Long,
    // properties from product
    val productId: Long,
    val name: String,
    val description: String,
    val price: Double,
    val currency: String,
    val availableColors: List<Color>,
    val availableSizes: List<Size>,
    val images: List<String>,
    // order selections
    val selectedSize: Size?,
    val selectedColor: Color?
)