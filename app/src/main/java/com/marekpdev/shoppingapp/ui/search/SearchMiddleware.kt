package com.marekpdev.shoppingapp.ui.search

import android.util.Log
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.repository.Data
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

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

        // 1. todo need to add these calls to disposable somehow
        // 2. todo need to cancel previous request (by using switchMap?)
        // see more info here
        // https://blog.mindorks.com/implement-search-using-rxjava-operators-c8882b64fe1d
        getProducts()
            .map { it.filter { product -> product.name.contains(action.query, true) } }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map<SearchAction> {
                SearchAction.SearchSuccess(it)
            }
            .startWithItem(SearchAction.SearchStarted(action.query))
            .onErrorReturn { e -> SearchAction.SearchError(e) }
            .subscribe {
                requestAction(it)
            }

        // not sure if it should look like this
//        Observable.just(action)
//            .flatMap {
//                getProducts()
//                    .map { it.filter { product -> product.name.contains(action.query, true) } }
//                    .map<SearchAction>{ result -> SearchAction.SearchSuccess(result)}
//                    .onErrorReturn { e -> SearchAction.SearchError(e) }
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .startWithItem(SearchAction.SearchStarted(action.query))
//            }
//            .subscribe {
//                requestAction(it)
//            }
    }

    private fun getProducts(): Observable<List<Product>> {
//        return Observable.just(allProducts)
//            //.delay(2, TimeUnit.SECONDS)
        return Observable.create { emitter ->
            Thread.sleep(2000)
            emitter.onNext(allProducts)
            emitter.onComplete()
        }
    }

}