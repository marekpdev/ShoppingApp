package com.marekpdev.shoppingapp.ui.favourite

import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.repository.products.ProductsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 12/07/2022.
 */
class FavouriteMiddleware @Inject constructor(private val productsRepository: ProductsRepository)
    : Middleware<FavouriteState, FavouriteAction, FavouriteCommand> {

    override suspend fun bind(
        coroutineScope: CoroutineScope,
        state: StateFlow<FavouriteState>,
        requestAction: suspend (FavouriteAction) -> Unit
    ) {
        coroutineScope.launch {
            coroutineScope.launch {
                productsRepository.productsFlow()
                    .collectLatest { products ->
                        val currentState = state.value
                        val favouredProducts = products.filter { it.isFavoured }
                        requestAction(FavouriteAction.RefreshData(favouredProducts))
                    }
            }
        }
    }

    override suspend fun process(
        action: FavouriteAction,
        currentState: FavouriteState,
        requestAction: suspend (FavouriteAction) -> Unit,
        requestCommand: suspend (FavouriteCommand) -> Unit
    ) {
        when(action){
            is FavouriteAction.ToggleFavouriteClicked -> onToggleFavouriteClicked(action, currentState, requestAction, requestCommand)
            else -> {}
        }
    }

    private suspend fun onToggleFavouriteClicked(
        action: FavouriteAction.ToggleFavouriteClicked,
        currentState: FavouriteState,
        requestAction: suspend (FavouriteAction) -> Unit,
        requestCommand: suspend (FavouriteCommand) -> Unit
    ) {
        productsRepository.toggleFavourite(action.product)
    }

}