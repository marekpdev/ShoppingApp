package com.marekpdev.shoppingapp.repository.products

import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.repository.Menu
import kotlinx.coroutines.flow.StateFlow

/**
 * Created by Marek Pszczolka on 12/07/2021.
 */
interface ProductsRepository {

    fun getAllMenu(): StateFlow<Menu>

    suspend fun getMenuForCategory(categoryId: Int): Menu

    suspend fun getProduct(id: Long): Product?

    suspend fun toggleFavourite(product: Product): Product

}