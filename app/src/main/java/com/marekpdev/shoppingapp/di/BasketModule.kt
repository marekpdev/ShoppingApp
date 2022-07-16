package com.marekpdev.shoppingapp.di

import com.marekpdev.shoppingapp.repository.basket.BasketRepository
import com.marekpdev.shoppingapp.repository.basket.BasketRepositoryImpl
import com.marekpdev.shoppingapp.repository.products.ProductsRepositoryImpl
import com.marekpdev.shoppingapp.repository.products.ProductsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Marek Pszczolka on 12/07/2021.
 */
@Module
@InstallIn(SingletonComponent::class)
object BasketModule {

}

@Module
@InstallIn(SingletonComponent::class)
interface BasketDependencyBinder {

    @Binds
    fun bindBasketRepository(repositoryImpl: BasketRepositoryImpl): BasketRepository

}

