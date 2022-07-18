package com.marekpdev.shoppingapp.di

import com.marekpdev.shoppingapp.repository.Menu
import com.marekpdev.shoppingapp.repository.products.ProductsRepository
import com.marekpdev.shoppingapp.ui.search.*
import com.marekpdev.shoppingapp.ui.search.filter.Filters
import com.marekpdev.shoppingapp.ui.search.sort.SortType
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
class SearchModule {

    @Provides
    @Singleton
    fun provideSearchMiddleware(productsRepository: ProductsRepository) = SearchMiddleware(productsRepository)

    @Provides
    @Singleton
    fun provideSearchNavigationMiddleware() = SearchNavigationMiddleware()

    @Provides
    @Singleton
    fun provideSearchFiltersMiddleware(productsRepository: ProductsRepository) = SearchFiltersMiddleware(productsRepository)

    @Provides
    @Singleton
    fun provideSearchStore(
        searchMiddleware: SearchMiddleware,
        searchNavigationMiddleware: SearchNavigationMiddleware,
        searchFiltersMiddleware: SearchFiltersMiddleware
    ): SearchStore {
        return SearchStore(
            SearchState("", false, "", Menu(emptyList(), emptyList()), SortType.INIT, Filters.INIT),
            listOf(
                searchMiddleware,
                searchNavigationMiddleware,
                searchFiltersMiddleware
            ),
            SearchReducer()
        )
    }

}