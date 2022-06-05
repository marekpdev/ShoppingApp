package com.marekpdev.shoppingapp.ui.search

import android.util.Log
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.repository.Data
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

/**
 * Created by Marek Pszczolka on 05/06/2022.
 */
class SearchMiddleware: Middleware<SearchState, SearchAction, SearchCommand> {

    private val allProducts = Data.getMenu().second!!

    override fun process(
        action: SearchAction,
        currentState: SearchState,
        requestAction: (SearchAction) -> Unit,
        requestCommand: (SearchCommand) -> Unit
    ) {
        when(action){
            is SearchAction.SearchQueryChanged -> {
                processSearchQueryChanged(action, requestAction)
            }
            else -> {

            }
        }
    }

    private fun processSearchQueryChanged(
        action: SearchAction.SearchQueryChanged,
        requestAction: (SearchAction) -> Unit
    ) {

        // not sure if it should look like this
        Observable.just(action)
            .flatMap {
                getProducts()
                    .map { it.filter { product -> product.name.contains(action.query, true) } }
                    .map<SearchAction>{ result -> SearchAction.SearchSuccess(result)}
                    .onErrorReturn { e -> SearchAction.SearchError(e) }
                    .observeOn(AndroidSchedulers.mainThread())
                    .startWithItem(SearchAction.SearchStarted(action.query))
            }
            .subscribe {
                requestAction(it)
            }
    }

    private fun getProducts(): Observable<List<Product>> {
        return Observable.just(allProducts)
            //.delay(2, TimeUnit.SECONDS)
    }
}