package com.marekpdev.shoppingapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.marekpdev.shoppingapp.models.Product
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Maybe

/**
 * Created by Marek Pszczolka on 11/07/2021.
 */
@Dao
interface ProductsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(product: Product)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg products: Product)

    @Update
    fun update(product: Product)

    @Query("SELECT * from products WHERE id = :id")
    //@Query("SELECT * FROM products WHERE id IS :id") OR THIS?
    fun getProduct(id: Long): Product

    @Delete
    fun delete(product: Product)

    @Query("DELETE FROM products")
    fun clear()

    @Query("SELECT * FROM products")
    fun getAllProducts(): LiveData<List<Product>>

    // todo should we return LiveData from room ? or LiveData only should be used in ViewModels?

}