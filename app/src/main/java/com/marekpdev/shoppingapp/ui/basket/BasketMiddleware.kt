package com.marekpdev.shoppingapp.ui.basket

import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.repository.basket.BasketRepository
import com.marekpdev.shoppingapp.repository.products.ProductsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 25/07/2022.
 */
class BasketMiddleware @Inject constructor(private val productsRepository: ProductsRepository,
                                           private val basketRepository: BasketRepository)
    :Middleware<BasketState, BasketAction, BasketCommand> {

    override suspend fun bind(
        coroutineScope: CoroutineScope,
        state: StateFlow<BasketState>,
        requestAction: suspend (BasketAction) -> Unit
    ) {
        coroutineScope.launch {
            basketRepository.observeBasketProducts()
                .collectLatest { basketProducts ->
                    requestAction(BasketAction.RefreshData(basketProducts, basketProducts.sumOf { it.price }))
                }
        }
    }

    override suspend fun process(
        action: BasketAction,
        currentState: BasketState,
        requestAction: suspend (BasketAction) -> Unit,
        requestCommand: suspend (BasketCommand) -> Unit
    ) {

    }
}