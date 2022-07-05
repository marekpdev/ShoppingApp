package com.marekpdev.shoppingapp.repository.basket

import com.marekpdev.shoppingapp.models.Product

/**
 * Created by Marek Pszczolka on 05/07/2022.
 */
class BasketRepositoryImpl: BasketRepository {

    private val productsAdded = mutableListOf<Product>()

    override fun addProduct(product: Product) {
        productsAdded.add(product)
    }
}