package com.marekpdev.shoppingapp.di

import com.marekpdev.shoppingapp.repository.products.ProductsRepository
import com.marekpdev.shoppingapp.ui.favourite.*
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
class FavouriteModule {

    @Provides
    @Singleton
    fun provideFavouriteMiddleware(productsRepository: ProductsRepository) = FavouriteMiddleware(productsRepository)

    @Provides
    @Singleton
    fun provideFavouriteNavigationMiddleware() = FavouriteNavigationMiddleware()

    @Provides
    @Singleton
    fun provideFavouriteStore(
        favouriteMiddleware: FavouriteMiddleware,
        favouriteNavigationMiddleware: FavouriteNavigationMiddleware
    ): FavouriteStore {
        return FavouriteStore(
            FavouriteState(emptyList(),false),
            listOf(
                favouriteMiddleware,
                favouriteNavigationMiddleware
            ),
            FavouriteReducer()
        )
    }

}