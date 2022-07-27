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

    // TODO only for testing
    private var testProductsAdded = false

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

        coroutineScope.launch {
            productsRepository.getAllMenu()
                .collectLatest { menu ->
                    if(!testProductsAdded) {
                        menu.products.take(3).forEach {
                            basketRepository.addToBasket(
                                it,
                                it.availableSizes.firstOrNull(),
                                it.availableColors.firstOrNull()
                            )
                        }
                        testProductsAdded = true
                    }
                }
        }
    }

    override suspend fun process(
        action: BasketAction,
        currentState: BasketState,
        requestAction: suspend (BasketAction) -> Unit,
        requestCommand: suspend (BasketCommand) -> Unit
    ) {
        when(action){
            is BasketAction.RemoveBasketProduct -> onRemoveBasketProduct(action, currentState, requestAction, requestCommand)
            is BasketAction.UpdateSize -> onUpdateSize(action, currentState, requestAction, requestCommand)
            is BasketAction.UpdateColor -> onUpdateColor(action, currentState, requestAction, requestCommand)
        }
    }

    private suspend fun onRemoveBasketProduct(
        action: BasketAction.RemoveBasketProduct,
        currentState: BasketState,
        requestAction: suspend (BasketAction) -> Unit,
        requestCommand: suspend (BasketCommand) -> Unit
    ) {
        basketRepository.removeFromBasket(action.basketProduct)
    }

    private suspend fun onUpdateSize(
        action: BasketAction.UpdateSize,
        currentState: BasketState,
        requestAction: suspend (BasketAction) -> Unit,
        requestCommand: suspend (BasketCommand) -> Unit
    ) {
        basketRepository.updateSize(action.basketProduct, action.size)
    }

    private suspend fun onUpdateColor(
        action: BasketAction.UpdateColor,
        currentState: BasketState,
        requestAction: suspend (BasketAction) -> Unit,
        requestCommand: suspend (BasketCommand) -> Unit
    ) {
        basketRepository.updateColor(action.basketProduct, action.color)
    }
}