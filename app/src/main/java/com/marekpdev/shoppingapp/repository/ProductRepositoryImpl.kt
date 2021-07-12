package com.marekpdev.shoppingapp.repository

import com.marekpdev.shoppingapp.api.ProductsApi
import com.marekpdev.shoppingapp.db.ProductsDao
import com.marekpdev.shoppingapp.models.Product
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 12/07/2021.
 */

class ProductRepositoryImpl @Inject constructor(
    private val productsApi: ProductsApi,
    private val productsDao: ProductsDao
): ProductsRepository{

    override fun observeProducts(): Observable<List<Product>> {
        TODO("Not yet implemented")
    }
}