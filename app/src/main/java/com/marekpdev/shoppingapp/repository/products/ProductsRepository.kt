package com.marekpdev.shoppingapp.repository.products

import com.marekpdev.shoppingapp.models.Product
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

/**
 * Created by Marek Pszczolka on 12/07/2021.
 */
interface ProductsRepository {

//    fun observeProducts(): Observable<List<Product>>
//
//    fun observeProduct(): Observable<Product>

    fun getProduct(id: Long): Single<Product>

    fun getProducts(): Single<List<Product>>

    fun observeProducts(): Observable<List<Product>>

    fun toggleFavourite(product: Product): Completable

}