package com.marekpdev.shoppingapp.ui.search

import android.widget.SearchView
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.mvi.Store
import com.marekpdev.shoppingapp.repository.Data
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.CompletableSource
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.SingleSource

/**
 * Created by Marek Pszczolka on 04/06/2022.
 */
class SearchMiddleware: Middleware<SearchViewState, SearchAction> {

    private val allProducts = Data.getMenu().second!!

    override fun process(
        action: SearchAction,
        currentState: SearchViewState,
        store: Store<SearchViewState, SearchAction>
    ) {
        when(action){
            is SearchAction.SearchQueryChanged -> {
                processSearchQueryChanged(action, store)
            }
            else -> {

            }
        }
    }

    private fun processSearchQueryChanged(
        action: SearchAction.SearchQueryChanged,
        store: Store<SearchViewState, SearchAction>
    ) {

        // not sure if it should look like this
        Observable.just(action)
            .flatMap {
                getProducts()
                    .map { it.filter { product -> product.name.contains(action.query) } }
                    .map<SearchAction>{ result -> SearchAction.SearchSuccess(result)}
                    .onErrorReturn { e -> SearchAction.SearchError(e) }
                    .observeOn(AndroidSchedulers.mainThread())
                    .startWithItem(SearchAction.SearchStarted)
            }.subscribe {
                store.dispatch(it)
            }
    }

    private fun getProducts(): Observable<List<Product>>{
        return Observable.just(allProducts)
    }
}