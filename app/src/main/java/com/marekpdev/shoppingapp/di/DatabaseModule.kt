package com.marekpdev.shoppingapp.di

import android.content.Context
import androidx.room.Room
import com.marekpdev.shoppingapp.db.AppDatabase
import com.marekpdev.shoppingapp.db.ProductsDao
import dagger.Module
import dagger.Provides

/**
 * Created by Marek Pszczolka on 12/07/2021.
 */
@Module
object DatabaseModule {

    @Provides
    fun provideDatabase(context: Context): AppDatabase = AppDatabase.getInstance(context)

    @Provides
    fun provideProductsDao(db: AppDatabase): ProductsDao = db.getProductsDao()

}