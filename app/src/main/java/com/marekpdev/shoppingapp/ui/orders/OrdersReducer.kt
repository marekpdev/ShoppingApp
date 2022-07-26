package com.marekpdev.shoppingapp.ui.orders

import com.marekpdev.shoppingapp.mvi.Reducer

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
class OrdersReducer: Reducer<OrdersState, OrdersAction> {

    override fun reduce(currentState: OrdersState, action: OrdersAction): OrdersState {
        return when (action){
            is OrdersAction.Loading -> {
                currentState.copy(loading = true)
            }
            is OrdersAction.RefreshData -> {
                currentState.copy(
                    orders = action.orders,
                    loading = false
                )
            }
            else -> {
                currentState
            }
        }
    }

}