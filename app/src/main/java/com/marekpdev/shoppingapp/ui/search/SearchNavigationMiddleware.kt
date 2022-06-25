package com.marekpdev.shoppingapp.ui.search

import com.jakewharton.rxrelay3.PublishRelay
import com.marekpdev.shoppingapp.mvi.Middleware
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable

/**
 * Created by Marek Pszczolka on 05/06/2022.
 */
class SearchNavigationMiddleware: Middleware<SearchState, SearchAction, SearchCommand> {

    override fun bind(
        actions: PublishRelay<SearchAction>,
        commands: PublishRelay<SearchCommand>,
        state: Flowable<SearchState>
    ): Observable<SearchAction> {
        return actions.doOnNext { action ->
            process(action, actions::accept, commands::accept)
        }
    }

    fun process(
        action: SearchAction,
        //currentState: SearchState,
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
            is SearchAction.SortConfirmed -> {
                requestCommand(SearchCommand.HideSortBottomSheet)
            }
            is SearchAction.FilterClicked -> {
                requestCommand(SearchCommand.ShowFilterBottomSheet)
            }
            is SearchAction.FilterConfirmed -> {
                requestCommand(SearchCommand.HideFilterBottomSheet)
            }
            else -> {

            }
        }
    }

}