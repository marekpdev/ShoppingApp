package com.marekpdev.shoppingapp.di

import com.marekpdev.shoppingapp.repository.basket.BasketRepository
import com.marekpdev.shoppingapp.repository.products.ProductsRepository
import com.marekpdev.shoppingapp.repository.products.ProductsRepositoryImpl
import com.marekpdev.shoppingapp.ui.product.ProductMiddleware
import com.marekpdev.shoppingapp.ui.product.ProductReducer
import com.marekpdev.shoppingapp.ui.product.ProductState
import com.marekpdev.shoppingapp.ui.product.ProductStore
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Marek Pszczolka on 12/07/2021.
 */
@Module
@InstallIn(SingletonComponent::class)
object ProductsModule {

    @Provides
    @Singleton
    fun provideProductMiddleware(productsRepository: ProductsRepository, basketRepository: BasketRepository) = ProductMiddleware(productsRepository, basketRepository)

    @Provides
    @Singleton
    fun provideProductStore(
        productMiddleware: ProductMiddleware,
    ): ProductStore {
        return ProductStore(
            ProductState(null, null, null, false),
            listOf(
                productMiddleware
            ),
            ProductReducer()
        )
    }

}

@Module
@InstallIn(SingletonComponent::class)
interface ProductsDependencyBinder {

    @Binds
    @Singleton
    fun bindProductsRepository(repositoryImpl: ProductsRepositoryImpl): ProductsRepository

}

