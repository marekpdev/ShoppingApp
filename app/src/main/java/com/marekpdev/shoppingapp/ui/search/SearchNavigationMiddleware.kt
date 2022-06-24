package com.marekpdev.shoppingapp.ui.search

import com.marekpdev.shoppingapp.mvi.Middleware

/**
 * Created by Marek Pszczolka on 05/06/2022.
 */
class SearchNavigationMiddleware: Middleware<SearchState, SearchAction, SearchCommand> {

    override fun process(
        action: SearchAction,
        currentState: SearchState,
        requestAction: (SearchAction) -> Unit,
        requestCommand: (SearchCommand) -> Unit
    ) {
        when(action){
            is SearchAction.ProductClicked -> {
                requestCommand(SearchCommand.GoToProductDetailsScreen(action.productId))
            }
            is SearchAction.SortClicked -> {
                requestCommand(SearchCommand.ShowSortBottomSheet)
            }
            is SearchAction.FilterClicked -> {
                requestCommand(SearchCommand.ShowFilterBottomSheet)
            }
            else -> {

            }
        }
    }

}