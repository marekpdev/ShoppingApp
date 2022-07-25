package com.marekpdev.shoppingapp.ui.basket

import com.marekpdev.shoppingapp.mvi.Middleware
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

/**
 * Created by Marek Pszczolka on 25/07/2022.
 */
class BasketNavigationMiddleware: Middleware<BasketState, BasketAction, BasketCommand> {

    override suspend fun bind(
        coroutineScope: CoroutineScope,
        state: StateFlow<BasketState>,
        requestAction: suspend (BasketAction) -> Unit
    ) {

    }

    override suspend fun process(
        action: BasketAction,
        currentState: BasketState,
        requestAction: suspend (BasketAction) -> Unit,
        requestCommand: suspend (BasketCommand) -> Unit
    ) {
        when(action){
            is BasketAction.BasketProductClicked -> requestCommand(BasketCommand.EditBasketProduct(action.basketProductId))
        }
    }
}