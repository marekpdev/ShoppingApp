package com.marekpdev.shoppingapp.ui.product

import android.util.Log
import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.repository.basket.BasketRepository
import com.marekpdev.shoppingapp.repository.products.ProductsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 05/07/2022.
 */
class ProductMiddleware @Inject constructor(
    private val productsRepository: ProductsRepository,
    private val basketRepository: BasketRepository
): Middleware<ProductState, ProductAction, ProductCommand> {

    override suspend fun bind(
        coroutineScope: CoroutineScope,
        state: StateFlow<ProductState>,
        requestAction: suspend (ProductAction) -> Unit
    ) {

    }

    override suspend fun process(
        action: ProductAction,
        currentState: ProductState,
        requestAction: suspend (ProductAction) -> Unit,
        requestCommand: suspend (ProductCommand) -> Unit
    ) {
        Log.d("FEO900", "ProductMiddleware process $action")
        when(action){
            is ProductAction.FetchProduct -> onFetchProduct(action, currentState, requestAction, requestCommand)
            is ProductAction.AddProductClicked -> onAddProductClicked(action, currentState, requestAction, requestCommand)
            else -> {}
        }
    }


    private suspend fun onFetchProduct(
        action: ProductAction.FetchProduct,
        currentState: ProductState,
        requestAction: suspend (ProductAction) -> Unit,
        requestCommand: suspend (ProductCommand) -> Unit
    ) {
        Log.d("FEO920", "ProductMiddleware onFetchProduct 1")
        requestAction(ProductAction.Loading)
        Log.d("FEO920", "ProductMiddleware onFetchProduct 2")
        val product = productsRepository.getProduct(action.productId)
        Log.d("FEO920", "ProductMiddleware onFetchProduct 3")
        if(product != null) {
            Log.d("FEO920", "ProductMiddleware onFetchProduct 4")
            requestAction(ProductAction.ProductFetched(product))
        } else {
            Log.d("FEO920", "ProductMiddleware onFetchProduct 5")
            requestAction(ProductAction.ProductError(Exception("Product not found")))
        }
    }

    private suspend fun onAddProductClicked(
        action: ProductAction.AddProductClicked,
        currentState: ProductState,
        requestAction: suspend (ProductAction) -> Unit,
        requestCommand: suspend (ProductCommand) -> Unit
    ) {
        currentState.product?.let { basketRepository.addProduct(it) }
        requestCommand(ProductCommand.ProductAddedToBasket)
    }

}