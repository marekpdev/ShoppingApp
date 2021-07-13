package com.marekpdev.shoppingapp.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.marekpdev.shoppingapp.api.ProductsApi
import com.marekpdev.shoppingapp.api.RetrofitProvider
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by Marek Pszczolka on 12/07/2021.
 */
@Module
object NetworkModule {

    @Provides
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    // todo shouldn't we just skip having retrofit provider and have all internal workflow
    // added in NetworkModule directly?
    @Provides
    fun provideRetrofit(retrofitProvider: RetrofitProvider): Retrofit = retrofitProvider.getRetrofit()

    @Provides
    fun provideProductsApi(retrofit: Retrofit): ProductsApi = retrofit.create(ProductsApi::class.java)

}