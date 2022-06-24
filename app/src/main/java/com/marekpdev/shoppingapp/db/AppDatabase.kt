package com.marekpdev.shoppingapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.marekpdev.shoppingapp.models.Product

/**
 * Created by Marek Pszczolka on 11/07/2021.
 */

// todo should i merge 'db' package with 'storage' package?
// or should i also rename it to 'local'

// also instead of 'api' should i just use names like 'remote' vs 'local'?

@Database(entities = [Product::class], version = 1, exportSchema = false)
@TypeConverters(
    ProductListConverter::class,
    ColorListConverter::class,
    SizeListConverter::class,
    StringListConverter::class,
)
abstract class AppDatabase : RoomDatabase() {

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        // todo ??? is this how we should have data base name here?
        //  or extract it out to Database class as constant
        // also do i need to use some singletons here? i feel like when we
        // have context we shouldn't do singletons???
        fun getInstance(context: Context): AppDatabase {
            synchronized(this){
                var instance = INSTANCE

                if(instance == null){
                    instance = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        "${AppDatabase::class.java.simpleName}.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }

                return instance
            }
            // todo
            // there might be better way to provide singleton with thread safety
            // maybe use some more fluent workflow that includes .also { instance = it} etc
            // or just use kotlin object?
        }
    }

    abstract fun getProductsDao(): ProductsDao
    // todo should it be
    // abstract val productsDao: ProductsDao
    // ??
}