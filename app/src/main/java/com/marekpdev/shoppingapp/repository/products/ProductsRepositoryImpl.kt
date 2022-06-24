package com.marekpdev.shoppingapp.repository.products

import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.repository.Data


/**
 * Created by Marek Pszczolka on 12/07/2021.
 */

class ProductsRepositoryImpl constructor(
   // private val productsApi: ProductsApi,
    //private val productsDao: ProductsDao
): ProductsRepository {

    override fun getProduct(id: Long): Product {
        return Data.getProduct(1, 1)
    }
}