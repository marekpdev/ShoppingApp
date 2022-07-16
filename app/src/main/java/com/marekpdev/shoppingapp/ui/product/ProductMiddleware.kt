package com.marekpdev.shoppingapp.ui.product

import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.repository.basket.BasketRepository
import com.marekpdev.shoppingapp.repository.products.ProductsRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.ofType
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 05/07/2022.
 */
class ProductMiddleware @Inject constructor(
    private val productsRepository: ProductsRepository,
    private val basketRepository: BasketRepository
): Middleware<ProductState, ProductAction, ProductCommand> {

    override fun bind(
        actions: Observable<ProductAction>,
        state: Observable<ProductState>,
        requestAction: (ProductAction) -> Unit,
        requestCommand: (ProductCommand) -> Unit
    ): Observable<ProductAction> {
        return actions.publish { shared ->
            Observable.merge(
                bindFetchProduct(shared.ofType(), state, requestAction, requestCommand),
                bindAddProductClicked(shared.ofType(), state, requestAction, requestCommand)
            )
        }
    }

    private fun bindFetchProduct(
        actions: Observable<ProductAction.FetchProduct>,
        state: Observable<ProductState>,
        requestAction: (ProductAction) -> Unit,
        requestCommand: (ProductCommand) -> Unit
    ): Observable<ProductAction> {
        return actions
            .withLatestFrom(state) { action, currentState -> action to currentState }
            .flatMap { (action, currentState) ->
                getProduct(action.productId, requestAction)
            }
    }

    private fun bindAddProductClicked(
        actions: Observable<ProductAction.AddProductClicked>,
        state: Observable<ProductState>,
        requestAction: (ProductAction) -> Unit,
        requestCommand: (ProductCommand) -> Unit
    ): Observable<ProductAction> {
        return actions
            .withLatestFrom(state) { action, currentState -> action to currentState }
            .doOnNext { (action, currentState) ->
                currentState.product?.let { basketRepository.addProduct(it) }
                requestCommand(ProductCommand.ProductAddedToBasket)
            }.map {
                it.first
            }
    }

    private fun getProduct(productId: Long, requestAction: (ProductAction) -> Unit): Observable<ProductAction> {
        return productsRepository.getProduct(productId)
            .toObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map<ProductAction> { ProductAction.ProductFetched(it) }
            .startWithItem(ProductAction.Loading)
            .onErrorReturn { e -> ProductAction.ProductError(e) }
            .doOnNext { requestAction(it) }
    }

}