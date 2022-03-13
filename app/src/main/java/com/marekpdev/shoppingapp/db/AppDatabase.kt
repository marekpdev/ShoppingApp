package com.marekpdev.shoppingapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.marekpdev.shoppingapp.models.Product

/**
 * Created by Marek Pszczolka on 11/07/2021.
 */

// todo should i merge 'db' package with 'storage' package?
// or should i also rename it to 'local'

// also instead of 'api' should i just use names like 'remote' vs 'local'?

//@Database(version = 1, entities = [Product::class], exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {

        // todo ??? is this how we should have data base name here?
        //  or extract it out to Database class as constant
        // also do i need to use some singletons here? i feel like when we
        // have context we shouldn't do singletons???
        fun getInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "${AppDatabase::class.java.simpleName}.db"
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun getProductsDao(): ProductsDao
}