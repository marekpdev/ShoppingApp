package com.marekpdev.shoppingapp.di

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
    fun provideSearchStore(): SearchStore {
        return SearchStore(
            SearchState("", false, "", emptyList(), SortType.INIT, Filters.INIT),
            listOf(SearchMiddleware(), SearchNavigationMiddleware(), SearchFiltersMiddleware()),
            SearchReducer()
        )
    }

}