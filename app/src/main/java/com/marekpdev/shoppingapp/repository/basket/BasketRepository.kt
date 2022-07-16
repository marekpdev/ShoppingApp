package com.marekpdev.shoppingapp.repository.basket

import com.marekpdev.shoppingapp.models.Product
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

/**
 * Created by Marek Pszczolka on 12/07/2021.
 */
interface BasketRepository {

    fun addProduct(product: Product)

}