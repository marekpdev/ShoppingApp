package com.marekpdev.shoppingapp.ui.product

import android.util.Log
import com.marekpdev.shoppingapp.mvi.Reducer

/**
 * Created by Marek Pszczolka on 05/07/2022.
 */
class ProductReducer: Reducer<ProductState, ProductAction> {

    override fun reduce(currentState: ProductState, action: ProductAction): ProductState {
        return when(action){
            is ProductAction.Loading -> {
                currentState.copy(loading = true)
            }
            is ProductAction.FetchProduct -> {
                currentState.copy(
                    product = null,
                    selectedSize = null,
                    selectedColor = null,
                    loading = false
                )
            }
            is ProductAction.ProductFetched -> {
                currentState.copy(
                    product = action.product,
                    selectedSize = null,
                    selectedColor = null,
                    loading = false
                )
            }
            is ProductAction.ProductError -> {
                currentState.copy(
                    product = null,
                    selectedSize = null,
                    selectedColor = null,
                    loading = false
                )
            }
            is ProductAction.ColorSelected -> {
                currentState.copy(
                    selectedColor = action.color
                )
            }
            is ProductAction.SizeSelected -> {
                currentState.copy(
                    selectedSize = action.size
                )
            }
            else -> currentState
        }
    }
}