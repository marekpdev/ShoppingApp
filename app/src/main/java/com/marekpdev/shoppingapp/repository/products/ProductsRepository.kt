package com.marekpdev.shoppingapp.repository.products

import com.marekpdev.shoppingapp.models.Product
import io.reactivex.rxjava3.core.Observable

/**
 * Created by Marek Pszczolka on 12/07/2021.
 */
interface ProductsRepository {

//    fun observeProducts(): Observable<List<Product>>
//
//    fun observeProduct(): Observable<Product>

    fun getProduct(id: Long): Observable<Product>

    fun getProducts(): Observable<List<Product>>

}