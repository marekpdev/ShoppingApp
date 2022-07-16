package com.marekpdev.shoppingapp.repository.basket

import com.marekpdev.shoppingapp.models.Product
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 05/07/2022.
 */
class BasketRepositoryImpl @Inject constructor(): BasketRepository {

    private val productsAdded = mutableListOf<Product>()

    override suspend fun addProduct(product: Product) {
        productsAdded.add(product)
    }
}