package com.marekpdev.shoppingapp.ui.basket

import com.marekpdev.shoppingapp.mvi.Reducer

/**
 * Created by Marek Pszczolka on 25/07/2022.
 */
class BasketReducer: Reducer<BasketState, BasketAction> {

    override fun reduce(currentState: BasketState, action: BasketAction): BasketState {
        return when(action){
            is BasketAction.Loading -> {
                currentState.copy(loading = true)
            }
            is BasketAction.RefreshData -> {
                currentState.copy(
                    basketProducts = action.basketProducts,
                    totalCost = action.totalCost
                )
            }
            else -> currentState
        }
    }
}