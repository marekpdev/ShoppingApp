package com.marekpdev.shoppingapp.di

import android.content.Context
import androidx.room.Room
import com.marekpdev.shoppingapp.db.Database
import com.marekpdev.shoppingapp.db.ProductsDao
import dagger.Module
import dagger.Provides

/**
 * Created by Marek Pszczolka on 12/07/2021.
 */
@Module
object DatabaseModule {

    // todo ??? is this how we should have data base name here? or extract it out to Database class as constant
    @Provides
    fun provideDatabase(context: Context): Database =
        Room.databaseBuilder(context, Database::class.java, "${Database::class.java.simpleName}.db").build()

    @Provides
    fun provideProductsDao(db: Database): ProductsDao = db.getProductsDao()

}