package com.marekpdev.shoppingapp.ui.search

import com.marekpdev.shoppingapp.mvi.Reducer

/**
 * Created by Marek Pszczolka on 04/06/2022.
 */
class SearchReducer: Reducer<SearchState, SearchAction> {
    override fun reduce(currentState: SearchState, action: SearchAction): SearchState {
        return when(action){
            is SearchAction.SearchQueryChanged -> {
                currentState.copy(searchQuery = action.query)
            }
            is SearchAction.Loading -> {
                currentState.copy(searchInProgress = true)
            }
            is SearchAction.SearchSuccess -> {
                currentState.copy(
                    searchInProgress = false,
                    searchSummary = "Showing ${action.products.size} items",
                    products = action.products
                )
            }
            is SearchAction.SearchError -> {
                currentState.copy(
                    searchInProgress = false,
                    searchSummary = "Search failed ${action.error?.toString()}",
                    products = emptyList()
                )
            }
            is SearchAction.SelectSortType -> {
                currentState.copy(
                    sortType = action.sortType
                )
            }
            is SearchAction.InitFilters -> {
                currentState.copy(
                    filters = action.filters
                )
            }
            is SearchAction.FilterSelectedPriceRangeChanged -> {
                currentState.copy(
                    filters = currentState.filters.copy(
                        priceRange = currentState.filters.priceRange.copy(
                            selected = action.selectedPriceRange
                        )
                    )
                )
            }
            else -> currentState
        }
    }

}