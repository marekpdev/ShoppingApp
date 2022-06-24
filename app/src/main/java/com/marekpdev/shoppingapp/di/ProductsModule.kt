package com.marekpdev.shoppingapp.di

import com.marekpdev.shoppingapp.repository.products.ProductsRepositoryImpl
import com.marekpdev.shoppingapp.repository.products.ProductsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


/**
 * Created by Marek Pszczolka on 12/07/2021.
 */
@Module
@InstallIn(SingletonComponent::class)
class ProductsModule {

    @Provides
    fun provideProductsRepository(): ProductsRepository {
        return ProductsRepositoryImpl()
    }

//    @Binds
//    //@Singleton // TODO ???? is Singleton/AppScope needed
//    @AppScope
//    abstract fun bindProductsRepository(repositoryImpl: ProductRepositoryImpl): ProductsRepository
}