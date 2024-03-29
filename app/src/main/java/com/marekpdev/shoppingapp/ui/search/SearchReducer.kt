package com.marekpdev.shoppingapp.ui.search

import com.marekpdev.shoppingapp.mvi.Reducer
import com.marekpdev.shoppingapp.repository.Menu

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
            is SearchAction.RefreshData -> {
                currentState.copy(
                    searchInProgress = false,
                    searchSummary = action.searchSummary,
                    menu = action.menu,
                    sortType = action.sortType,
                    filters = action.filters,
                    displayStates = action.displayStates
                )
            }
            is SearchAction.SearchError -> {
                currentState.copy(
                    searchInProgress = false,
//                    searchSummary = "Search failed ${action.error?.toString()}",
                    menu = Menu(emptyList(), emptyList())
                )
            }
            is SearchAction.InitFilters -> {
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