package com.marekpdev.shoppingapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.marekpdev.shoppingapp.models.Product

/**
 * Created by Marek Pszczolka on 11/07/2021.
 */
@Database(version = 1, entities = [Product::class], exportSchema = false)
abstract class Database : RoomDatabase() {

    abstract fun getProductsDao(): ProductsDao
}