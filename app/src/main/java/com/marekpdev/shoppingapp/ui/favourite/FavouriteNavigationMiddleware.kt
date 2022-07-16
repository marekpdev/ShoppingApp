package com.marekpdev.shoppingapp.ui.favourite

import com.marekpdev.shoppingapp.mvi.Middleware
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

/**
 * Created by Marek Pszczolka on 05/06/2022.
 */
class FavouriteNavigationMiddleware: Middleware<FavouriteState, FavouriteAction, FavouriteCommand> {


    override suspend fun process(
        action: FavouriteAction,
        state: FavouriteState,
        requestAction: suspend (FavouriteAction) -> Unit,
        requestCommand: suspend (FavouriteCommand) -> Unit
    ) {

    }

    override suspend fun bind(
        coroutineScope: CoroutineScope,
        state: StateFlow<FavouriteState>,
        requestAction: suspend (FavouriteAction) -> Unit
    ) {

    }


//    override fun bind(
//        actions: Observable<FavouriteAction>,
//        state: Observable<FavouriteState>,
//        requestAction: (FavouriteAction) -> Unit,
//        requestCommand: (FavouriteCommand) -> Unit
//    ): Observable<FavouriteAction> {
//        return actions.doOnNext { action ->
//            process(action, requestCommand)
//        }
//    }

    private fun process(
        action: FavouriteAction,
        requestCommand: (FavouriteCommand) -> Unit
    ) {
        when(action){
            is FavouriteAction.ProductClicked -> {
                requestCommand(FavouriteCommand.GoToProductDetailsScreen(action.productId))
            }
            else -> {

            }
        }
    }

}