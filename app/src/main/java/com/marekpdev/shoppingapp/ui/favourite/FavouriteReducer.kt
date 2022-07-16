package com.marekpdev.shoppingapp.ui.favourite

import android.util.Log
import com.marekpdev.shoppingapp.mvi.Reducer

/**
 * Created by Marek Pszczolka on 04/06/2022.
 */
class FavouriteReducer: Reducer<FavouriteState, FavouriteAction> {
    override fun reduce(currentState: FavouriteState, action: FavouriteAction): FavouriteState {
        return when(action){
            is FavouriteAction.Loading -> {
                currentState.copy(loading = true)
            }
            is FavouriteAction.RefreshData -> {
                currentState.copy(
                    loading = false,
                    products = action.products
                )
            }
            else -> currentState
        }
    }

}