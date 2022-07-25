package com.marekpdev.shoppingapp.di

import com.marekpdev.shoppingapp.repository.basket.BasketRepository
import com.marekpdev.shoppingapp.repository.basket.BasketRepositoryImpl
import com.marekpdev.shoppingapp.repository.products.ProductsRepository
import com.marekpdev.shoppingapp.ui.basket.BasketMiddleware
import com.marekpdev.shoppingapp.ui.basket.BasketReducer
import com.marekpdev.shoppingapp.ui.basket.BasketState
import com.marekpdev.shoppingapp.ui.basket.BasketStore
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
class BasketModule {

    @Provides
    @Singleton
    fun provideBasketMiddleware(
        productsRepository: ProductsRepository,
        basketRepository: BasketRepository
    ) = BasketMiddleware(productsRepository, basketRepository)

    @Provides
    @Singleton
    fun provideBasketStore(
        basketMiddleware: BasketMiddleware,
    ): BasketStore {
        return BasketStore(
            BasketState(emptyList(), false, 0.0),
            listOf(
                basketMiddleware
            ),
            BasketReducer()
        )
    }

}

@Module
@InstallIn(SingletonComponent::class)
interface BasketDependencyBinder {

    @Binds
    @Singleton
    fun bindBasketRepository(repositoryImpl: BasketRepositoryImpl): BasketRepository

}

