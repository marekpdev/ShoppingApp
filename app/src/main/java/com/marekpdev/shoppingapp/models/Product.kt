package com.marekpdev.shoppingapp.models

/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
data class Product (
    val id: Long,
    val name: String, // Eywa Hoodie
    val description: String,
    val price: Double, // 30.99
    val oldPrice: Double, // 44.99
    val currency: String, // $
    val availableColors: List<Color>,
    val availableSizes: List<Size>
)
