package com.marekpdev.shoppingapp.ui.search

import android.util.Log
import com.marekpdev.shoppingapp.mvi.Middleware
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

/**
 * Created by Marek Pszczolka on 05/06/2022.
 */
class SearchNavigationMiddleware: Middleware<SearchState, SearchAction, SearchCommand> {

    override suspend fun process(
        action: SearchAction,
        state: SearchState,
        requestAction: suspend (SearchAction) -> Unit,
        requestCommand: suspend (SearchCommand) -> Unit
    ) {

    }

    override suspend fun bind(
        coroutineScope: CoroutineScope,
        state: StateFlow<SearchState>,
        requestAction: suspend (SearchAction) -> Unit
    ) {

    }



    //    override fun bind(
//        actions: Observable<SearchAction>,
//        state: Observable<SearchState>,
//        requestAction: (SearchAction) -> Unit,
//        requestCommand: (SearchCommand) -> Unit
//    ): Observable<SearchAction> {
//        return actions.doOnNext { action ->
//            process(action, requestCommand)
//        }
//    }

    private fun process(
        action: SearchAction,
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