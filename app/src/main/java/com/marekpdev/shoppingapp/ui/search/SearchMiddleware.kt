package com.marekpdev.shoppingapp.ui.search

import android.util.Log
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.repository.Data
import com.marekpdev.shoppingapp.ui.search.filter.Filters
import com.marekpdev.shoppingapp.ui.search.sort.SortType
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
            is SearchAction.FetchInitialData -> {
                getProductsToShow(currentState.searchQuery, currentState.sortType, currentState.filters, true, requestAction)
            }
            is SearchAction.SearchQueryChanged -> {
                getProductsToShow(action.query, currentState.sortType, currentState.filters, false, requestAction)
            }
            is SearchAction.SortConfirmed -> {
                getProductsToShow(currentState.searchQuery, currentState.sortType.confirmSelection(), currentState.filters, false, requestAction)
            }
            is SearchAction.FilterConfirmed -> {
                getProductsToShow(currentState.searchQuery, currentState.sortType, currentState.filters.confirmSelection(), false, requestAction)
            }
            else -> {

            }
        }
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

    private fun getFilterRequirements(searchQuery: String, filters: Filters) = listOf<(Product) -> Boolean>(
        { it.name.contains(searchQuery, true) },
        { it.price.toInt() in filters.priceRange.applied},
        { it.availableSizes.any { size -> filters.sizes.applied.contains(size) }},
        { it.availableColors.any { color -> filters.colors.applied.contains(color) }},
    )

    private fun getProductsToShow (
        searchQuery: String,
        sortType: SortType,
        filters: Filters,
        isInitial: Boolean,
        requestAction: (SearchAction) -> Unit
    ) {
        // 1. todo need to add these calls to disposable somehow
        // 2. todo need to cancel previous request (by using switchMap?)
        // see more info here
        // https://blog.mindorks.com/implement-search-using-rxjava-operators-c8882b64fe1d
        val filterRequirements = getFilterRequirements(searchQuery, filters)

        Log.d("FEO120", "getProductsToShow $filters")

        getProducts()
            .map { it.filter { product -> isInitial || filterRequirements.all { predicate -> predicate(product) } } }
            .map { it.sortedWith(sortType.type.applied.comparator) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map<SearchAction> {
                if(isInitial) {
                    SearchAction.InitialDataFetched(it, sortType, filters)
                } else {
                    SearchAction.RefreshData(it, sortType, filters)
                }
            }
            .startWithItem(SearchAction.Loading)
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

}