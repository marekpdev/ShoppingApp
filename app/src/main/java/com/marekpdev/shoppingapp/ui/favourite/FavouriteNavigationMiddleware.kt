package com.marekpdev.shoppingapp.ui.favourite

import com.marekpdev.shoppingapp.mvi.Middleware
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

/**
 * Created by Marek Pszczolka on 05/06/2022.
 */
class FavouriteNavigationMiddleware: Middleware<FavouriteState, FavouriteAction, FavouriteCommand> {

    override suspend fun bind(
        coroutineScope: CoroutineScope,
        state: StateFlow<FavouriteState>,
        requestAction: suspend (FavouriteAction) -> Unit
    ) {

    }

    override suspend fun process(
        action: FavouriteAction,
        currentState: FavouriteState,
        requestAction: suspend (FavouriteAction) -> Unit,
        requestCommand: suspend (FavouriteCommand) -> Unit
    ) {
        when (action){
            is FavouriteAction.ProductClicked -> requestCommand(FavouriteCommand.GoToProductDetailsScreen(action.productId))
            else -> {}
        }
    }

}