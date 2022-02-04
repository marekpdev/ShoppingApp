package com.marekpdev.shoppingapp.db

import androidx.room.Dao
import androidx.room.Query
import com.marekpdev.shoppingapp.models.Product
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Maybe

/**
 * Created by Marek Pszczolka on 11/07/2021.
 */
//@Dao
interface ProductsDao {

    //@Query("SELECT * FROM products WHERE id IS :id")
    fun getProduct(id: Long): Maybe<Product>

   //@Query("SELECT * FROM products")
    fun getAllProducts(): Flowable<List<Product>>

}