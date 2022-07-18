package com.marekpdev.shoppingapp.ui.search

import android.util.Log
import com.marekpdev.shoppingapp.mvi.Reducer
import com.marekpdev.shoppingapp.repository.Menu

/**
 * Created by Marek Pszczolka on 04/06/2022.
 */
class SearchReducer: Reducer<SearchState, SearchAction> {
    override fun reduce(currentState: SearchState, action: SearchAction): SearchState {
        return when(action){
            is SearchAction.SearchQueryChanged -> {
                Log.d("FEO160", "Changed State ${action.query}")
                currentState.copy(searchQuery = action.query)
            }
            is SearchAction.Loading -> {
                currentState.copy(searchInProgress = true)
            }
            is SearchAction.InitialDataFetched -> {
                Log.d("FEO111", "INITIAL DATA FILTERS ${action.filters}")
                currentState.copy(
                    searchInProgress = false,
                    searchSummary = "Showing ${action.menu.products.size} items",
                    menu = action.menu,
                    sortType = action.sortType,
                    filters = action.filters
                )
            }
            is SearchAction.RefreshData -> {
                Log.d("FEO111", "REFRESH DATA FILTERS ${action.filters}")
                currentState.copy(
                    searchInProgress = false,
                    searchSummary = "Showing ${action.menu.products.size} items",
                    menu = action.menu,
                    sortType = action.sortType,
                    filters = action.filters
                )
            }
            is SearchAction.SearchError -> {
                currentState.copy(
                    searchInProgress = false,
                    searchSummary = "Search failed ${action.error?.toString()}",
                    menu = Menu(emptyList(), emptyList())
                )
            }
            is SearchAction.InitFilters -> {
                Log.d("FEO111", "INIT FILTERS")
                currentState.copy(
                    filters = action.filters
                )
            }
            is SearchAction.SortSelectedType -> {
                currentState.copy(
                    sortType = currentState.sortType.copy(
                       type = currentState.sortType.type.copy(
                           selected = action.sortType
                       )
                    )
                )
            }
            is SearchAction.FilterSelectedPriceRangeChanged -> {
                currentState.copy(
                    filters = currentState.filters?.copy(
                        priceRange = currentState.filters.priceRange.copy(
                            selected = action.selectedPriceRange
                        )
                    )
                )
            }
            is SearchAction.FilterSelectedSizeChanged -> {
                currentState.copy(
                    filters = currentState.filters?.copy(
                        sizes = currentState.filters.sizes.copy(
                            selected = currentState.filters.sizes.selected.toggleItem(action.selectedSize)
                        )
                    )
                )
            }
            is SearchAction.FilterSelectedColorChanged -> {
                currentState.copy(
                    filters = currentState.filters?.copy(
                        colors = currentState.filters.colors.copy(
                            selected = currentState.filters.colors.selected.toggleItem(action.selectedColor)
                        )
                    )
                )
            }
            else -> currentState
        }
    }

    private fun <T> List<T>.toggleItem(item: T): List<T>{
        val newItems = toMutableList()
        if(newItems.contains(item)) newItems.remove(item)
        else newItems.add(item)
        return newItems.toList()
    }

}