package com.marekpdev.shoppingapp.ui.search

import android.util.Log
import com.jakewharton.rxrelay3.PublishRelay
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.repository.Data
import com.marekpdev.shoppingapp.repository.products.ProductsRepository
import com.marekpdev.shoppingapp.ui.search.filter.Filters
import com.marekpdev.shoppingapp.ui.search.sort.SortType
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.ofType
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import java.lang.Exception
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 05/06/2022.
 */
class SearchMiddleware @Inject constructor(private val productsRepository: ProductsRepository)
    : Middleware<SearchState, SearchAction, SearchCommand> {

    override fun bind(
        actions: Observable<SearchAction>,
        state: Observable<SearchState>,
        requestAction: (SearchAction) -> Unit,
        requestCommand: (SearchCommand) -> Unit
    ): Observable<SearchAction> {
        return actions.publish { shared ->
            Observable.merge(
                bindFetchInitialData(shared.ofType(), state, requestAction, requestCommand),
                bindSearchQueryChanged(shared.ofType(), state, requestAction, requestCommand),
                bindSortConfirmed(shared.ofType(), state, requestAction, requestCommand),
                bindFilterConfirmed(shared.ofType(), state, requestAction, requestCommand),
            )
        }
    }

    // need to sort out issue with naming bind1, bind2 etc
    // (cannot use two same methods with name bind when they have
    // 1 -> actions: Observable<SearchAction.SearchQueryChanged>
    // 2 -> actions: Observable<SearchAction.SortConfirmed>
    // as it is being detected as the same method signature
    private fun bindFetchInitialData(
        actions: Observable<SearchAction.FetchInitialData>,
        state: Observable<SearchState>,
        requestAction: (SearchAction) -> Unit,
        requestCommand: (SearchCommand) -> Unit
    ): Observable<SearchAction> {
        return actions
            .withLatestFrom(state) { action, currentState -> action to currentState }
            .flatMap { (action, currentState) ->
                getProductsToShow(currentState.searchQuery, currentState.sortType, currentState.filters, true, requestAction)
            }
    }

    private fun bindSearchQueryChanged(
        actions: Observable<SearchAction.SearchQueryChanged>,
        state: Observable<SearchState>,
        requestAction: (SearchAction) -> Unit,
        requestCommand: (SearchCommand) -> Unit
    ): Observable<SearchAction> {
        return actions
            .debounce(400, TimeUnit.MILLISECONDS)
            .withLatestFrom(state) { action, currentState -> action to currentState }
            .switchMap { (action, currentState) ->
                getProductsToShow(action.query, currentState.sortType, currentState.filters, false, requestAction)
            }
    }

    private fun bindSortConfirmed(
        actions: Observable<SearchAction.SortConfirmed>,
        state: Observable<SearchState>,
        requestAction: (SearchAction) -> Unit,
        requestCommand: (SearchCommand) -> Unit
    ): Observable<SearchAction> {
        return actions
            .withLatestFrom(state) { action, currentState -> action to currentState }
            .flatMap { (action, currentState) ->
                getProductsToShow(currentState.searchQuery, currentState.sortType.confirmSelection(), currentState.filters, false, requestAction)
            }
    }

    private fun bindFilterConfirmed(
        actions: Observable<SearchAction.FilterConfirmed>,
        state: Observable<SearchState>,
        requestAction: (SearchAction) -> Unit,
        requestCommand: (SearchCommand) -> Unit
    ): Observable<SearchAction> {
        return actions
            .withLatestFrom(state) { action, currentState -> action to currentState }
            .flatMap { (action, currentState) ->
                getProductsToShow(currentState.searchQuery, currentState.sortType, currentState.filters.confirmSelection(), false, requestAction)
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
    ): Observable<SearchAction> {
        // 1. todo need to add these calls to disposable somehow
        // 2. todo need to cancel previous request (by using switchMap?)
        // see more info here
        // https://blog.mindorks.com/implement-search-using-rxjava-operators-c8882b64fe1d
        val filterRequirements = getFilterRequirements(searchQuery, filters)

        Log.d("FEO120", "getProductsToShow $filters")

        return productsRepository.getProducts()
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
            .doOnNext { requestAction(it) }
//            .subscribe {
//                requestAction(it)
//            }

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