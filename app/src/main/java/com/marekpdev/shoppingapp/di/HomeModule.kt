package com.marekpdev.shoppingapp.di

import com.marekpdev.shoppingapp.repository.products.ProductsRepository
import com.marekpdev.shoppingapp.ui.home.HomeMiddleware
import com.marekpdev.shoppingapp.ui.home.HomeReducer
import com.marekpdev.shoppingapp.ui.home.HomeState
import com.marekpdev.shoppingapp.ui.home.HomeStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Marek Pszczolka on 24/06/2022.
 */
@Module
@InstallIn(SingletonComponent::class)
class HomeModule {

    @Provides
    @Singleton
    fun provideHomeMiddleware(productsRepository: ProductsRepository) = HomeMiddleware(productsRepository)

    @Provides
    @Singleton
    fun provideHomeStore(
        homeMiddleware: HomeMiddleware
    ): HomeStore {
        return HomeStore(
            HomeState(listOf(), false, false),
            listOf(
                homeMiddleware
            ),
            HomeReducer()
        )
    }

}