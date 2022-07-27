package com.marekpdev.shoppingapp.ui.order

import android.util.Log
import com.marekpdev.shoppingapp.mvi.Reducer

/**
 * Created by Marek Pszczolka on 05/07/2022.
 */
class OrderReducer: Reducer<OrderState, OrderAction> {

    override fun reduce(currentState: OrderState, action: OrderAction): OrderState {
        return when(action){
            is OrderAction.Loading -> {
                currentState.copy(loading = true)
            }
            is OrderAction.FetchOrder -> {
                currentState.copy(
                    order = null,
                    loading = false
                )
            }
            is OrderAction.OrderFetched -> {
                currentState.copy(
                    order = action.order,
                    loading = false
                )
            }
            is OrderAction.OrderError -> {
                currentState.copy(
                    order = null,
                    loading = false
                )
            }
            else -> currentState
        }
    }
}