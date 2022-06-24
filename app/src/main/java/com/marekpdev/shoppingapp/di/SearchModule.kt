package com.marekpdev.shoppingapp.di

import com.marekpdev.shoppingapp.ui.search.*
import com.marekpdev.shoppingapp.ui.search.sort.SortType
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Marek Pszczolka on 24/06/2022.
 */
@Module
@InstallIn(SingletonComponent::class)
class SearchModule {

    @Provides
    fun provideSearchStore(): SearchStore {
        return SearchStore(
            SearchState("", false, "", emptyList(), SortType.PRICE_LOWEST_FIRST),
            listOf(SearchMiddleware(), SearchNavigationMiddleware()),
            SearchReducer()
        )
    }

}