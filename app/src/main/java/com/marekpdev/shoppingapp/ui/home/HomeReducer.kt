package com.marekpdev.shoppingapp.ui.home

import com.marekpdev.shoppingapp.mvi.Reducer

/**
 * Created by Marek Pszczolka on 20/07/2022.
 */
class HomeReducer: Reducer<HomeState, HomeAction> {

    override fun reduce(currentState: HomeState, action: HomeAction): HomeState {
        return when(action){
            is HomeAction.LoadingProductRecommendations -> {
                currentState.copy(
                    loadingProductRecommendations = true
                )
            }
            is HomeAction.RefreshProductRecommendations -> {
                currentState.copy(
                    productRecommendations = action.productRecommendations,
                    loadingProductRecommendations = false
                )
            }
            else -> {
                currentState
            }

        }
    }
}