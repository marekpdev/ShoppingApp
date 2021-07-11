package com.marekpdev.shoppingapp.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.marekpdev.shoppingapp.api.Api
import com.marekpdev.shoppingapp.api.RetrofitProvider
import com.marekpdev.shoppingapp.db.Database
import com.marekpdev.shoppingapp.db.ProductsDao
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by Marek Pszczolka on 11/07/2021.
 */

object AppDependencyProvider {

    // TODO move to network module?

    @Provides
    @JvmStatic
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @JvmStatic
    fun provideRetrofit(retrofitProvider: RetrofitProvider): Retrofit = retrofitProvider.getRetrofit()

    @Provides
    @JvmStatic
    fun provideApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)

    @JvmStatic
    fun provideDatabase(context: Context): Database =
        Room.databaseBuilder(context, Database::class.java, "${Database::class.java.simpleName}.db").build()

    @Provides
    @JvmStatic
    fun provideProductsDao(db: Database): ProductsDao = db.getProductsDao()
}