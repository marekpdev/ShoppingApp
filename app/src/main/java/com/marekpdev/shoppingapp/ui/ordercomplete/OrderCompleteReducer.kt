package com.marekpdev.shoppingapp.ui.ordercomplete

import com.marekpdev.shoppingapp.mvi.Reducer

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
class OrderCompleteReducer: Reducer<OrderCompleteState, OrderCompleteAction> {

    override fun reduce(currentState: OrderCompleteState, action: OrderCompleteAction): OrderCompleteState {
        return when(action){
            is OrderCompleteAction.Loading -> {
                currentState.copy(loading = true)
            }
            is OrderCompleteAction.FetchOrder -> {
                currentState.copy(
                    order = null,
                    loading = false
                )
            }
            is OrderCompleteAction.OrderFetched -> {
                currentState.copy(
                    order = action.order,
                    loading = false
                )
            }
            is OrderCompleteAction.OrderError -> {
                currentState.copy(
                    order = null,
                    loading = false
                )
            }
            else -> currentState
        }
    }

}