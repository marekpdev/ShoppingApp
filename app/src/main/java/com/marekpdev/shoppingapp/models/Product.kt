package com.marekpdev.shoppingapp.models

/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
data class Product (
    val id: Long,
    val name: String,
    val description: String,
    val price: Double,
    val oldPrice: Double?,
    val currency: String,
    val availableColors: List<Color>,
    val availableSizes: List<Size>,
    val images: List<String>
)
