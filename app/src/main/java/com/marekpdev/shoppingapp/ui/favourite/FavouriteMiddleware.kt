package com.marekpdev.shoppingapp.ui.favourite

import android.util.Log
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.repository.products.ProductsRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.ofType
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 12/07/2022.
 */
class FavouriteMiddleware @Inject constructor(private val productsRepository: ProductsRepository)
    : Middleware<FavouriteState, FavouriteAction, FavouriteCommand> {

    override suspend fun process(
        action: FavouriteAction,
        state: FavouriteState,
        requestAction: suspend (FavouriteAction) -> Unit,
        requestCommand: suspend (FavouriteCommand) -> Unit
    ) {

    }

    override suspend fun bind(
        coroutineScope: CoroutineScope,
        state: StateFlow<FavouriteState>,
        requestAction: suspend (FavouriteAction) -> Unit
    ) {

    }

    fun bind(
        actions: Observable<FavouriteAction>,
        state: Observable<FavouriteState>,
        requestAction: (FavouriteAction) -> Unit,
        requestCommand: (FavouriteCommand) -> Unit
    ): Observable<FavouriteAction> {
        return bindObserveProducts(requestAction)
    }

    private fun bindObserveProducts(
        requestAction: (FavouriteAction) -> Unit
    ): Observable<FavouriteAction> {
        Log.d("FEO410", "BINDING observe")
        return productsRepository
            .observeProducts()
            .map {
                Log.d("FEO410", "OBSERVE work")
                it.filter { product -> product.isFavoured }
            }
            .map<FavouriteAction> { FavouriteAction.RefreshData(it) }
            .doOnNext {
                Log.d("FEO410", "REQUESTED")
                requestAction(it)
            }
    }

//    private fun bindToggleFavourite(
//        actions: Observable<FavouriteAction.ToggleFavouriteClicked>,
//        state: Observable<FavouriteState>,
//        requestAction: (FavouriteAction) -> Unit,
//        requestCommand: (FavouriteCommand) -> Unit
//    ): Observable<FavouriteAction> {
//        return actions
//            .withLatestFrom(state) { action, currentState -> action to currentState }
//            .flatMapCompletable { (action, currentState) ->
//                productsRepository
//                    .toggleFavourite(action.product)
//                    .doOnComplete {
//                        Log.d("FEO410", "getProductsToShow")
//                        // TODO need to perform some better chaining to not call subscribe() here
//                        getProductsToShow(requestAction).subscribe()
//                    }
//            }
//            .toObservable()
//    }

    private fun bindFetchInitialData(
        actions: Observable<FavouriteAction.FetchInitialData>,
        state: Observable<FavouriteState>,
        requestAction: (FavouriteAction) -> Unit,
        requestCommand: (FavouriteCommand) -> Unit
    ): Observable<FavouriteAction> {
        return actions
            .withLatestFrom(state) { action, currentState -> action to currentState }
            .flatMap { (action, currentState) ->
                getProductsToShow(requestAction)
            }
    }

    private fun getProductsToShow (
        requestAction: (FavouriteAction) -> Unit
    ): Observable<FavouriteAction> {
        return productsRepository.getProducts()
            .toObservable()
            .map { it.filter { product -> product.isFavoured } }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map<FavouriteAction> { FavouriteAction.RefreshData(it) }
            .startWithItem(FavouriteAction.Loading)
            .onErrorReturn { e -> FavouriteAction.LoadingError(e) }
            .doOnNext { requestAction(it) }
    }

}