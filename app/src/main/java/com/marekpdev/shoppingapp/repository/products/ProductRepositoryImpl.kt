package com.marekpdev.shoppingapp.repository.products

import com.marekpdev.shoppingapp.di.AppScope
import com.marekpdev.shoppingapp.models.*
import com.marekpdev.shoppingapp.models.order.Order
import com.marekpdev.shoppingapp.models.order.PaymentMethod
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 12/07/2021.
 */

@AppScope
class ProductRepositoryImpl @Inject constructor(
   // private val productsApi: ProductsApi,
    //private val productsDao: ProductsDao
): ProductsRepository {

}