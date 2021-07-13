package com.marekpdev.shoppingapp.di

import com.marekpdev.shoppingapp.repository.products.ProductRepositoryImpl
import com.marekpdev.shoppingapp.repository.products.ProductsRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * Created by Marek Pszczolka on 12/07/2021.
 */
@Module
abstract class ProductsModule {

    @Binds
    @Singleton // TODO ???? is Singleton/AppScope needed
    @AppScope
    abstract fun bindProductsRepository(repositoryImpl: ProductRepositoryImpl): ProductsRepository
}