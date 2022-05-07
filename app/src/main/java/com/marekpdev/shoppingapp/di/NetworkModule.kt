package com.marekpdev.shoppingapp.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.marekpdev.shoppingapp.api.AuthApi
import com.marekpdev.shoppingapp.api.ProductsApi
import com.marekpdev.shoppingapp.api.RetrofitProvider
import com.marekpdev.shoppingapp.api.remote.FakeWebService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by Marek Pszczolka on 12/07/2021.
 */
@Module
object NetworkModule {

    @Provides
    @AppScope
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    // todo shouldn't we just skip having retrofit provider and have all internal workflow
    // added in NetworkModule directly?
    @Provides
    @AppScope
    fun provideRetrofit(retrofitProvider: RetrofitProvider): Retrofit = retrofitProvider.getRetrofit()

    @Provides
    @AppScope
    fun provideProductsApi(retrofit: Retrofit): ProductsApi = retrofit.create(ProductsApi::class.java)

    @Provides
    @AppScope
    //fun provideAuthApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)
    fun provideAuthApi(): AuthApi = FakeWebService()

}