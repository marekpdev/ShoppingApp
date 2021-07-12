package com.marekpdev.shoppingapp.di

import com.marekpdev.shoppingapp.repository.ProductRepositoryImpl
import com.marekpdev.shoppingapp.repository.ProductsRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * Created by Marek Pszczolka on 12/07/2021.
 */
@Module
abstract class ProductsModule {

    @Binds
    @Singleton // TODO ???? is it needed
    abstract fun bindProductsRepository(repositoryImpl: ProductRepositoryImpl): ProductsRepository
}