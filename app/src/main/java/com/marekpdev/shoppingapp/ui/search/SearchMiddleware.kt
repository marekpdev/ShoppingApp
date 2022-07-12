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
        return Observable.merge(
            observeProducts(state, requestAction),
            actions.publish { shared ->
                Observable.mergeArray(
                    bindSearchQueryChanged(shared.ofType(), state, productsRepository.observeProducts(), requestAction, requestCommand),
                    bindSortConfirmed(shared.ofType(), state, productsRepository.observeProducts(), requestAction, requestCommand),
                    bindFilterConfirmed(shared.ofType(), state, productsRepository.observeProducts(), requestAction, requestCommand),
                    bindToggleFavourite(shared.ofType(), state, requestAction, requestCommand),
                )
            }
        )
    }

    private fun observeProducts(
        state: Observable<SearchState>,
        requestAction: (SearchAction) -> Unit
    ): Observable<SearchAction> {
        return productsRepository
            .observeProducts()
            .withLatestFrom(state) { products, currentState -> products to currentState}
            .map { (products, currentState) ->
                Log.d("FEO510", "FROM observeProducts")
                getFilteredProducts(products, currentState.searchQuery, currentState.filters,currentState.sortType) to currentState
            }
            .map<SearchAction> { (products, currentState) ->
                SearchAction.RefreshData(products, currentState.sortType , currentState.filters)
            }
            .doOnNext { requestAction(it) }
    }

    // need to sort out issue with naming bind1, bind2 etc
    // (cannot use two same methods with name bind when they have
    // 1 -> actions: Observable<SearchAction.SearchQueryChanged>
    // 2 -> actions: Observable<SearchAction.SortConfirmed>
    // as it is being detected as the same method signature

    private fun bindSearchQueryChanged(
        actions: Observable<SearchAction.SearchQueryChanged>,
        state: Observable<SearchState>,
        productsObs: Observable<List<Product>>,
        requestAction: (SearchAction) -> Unit,
        requestCommand: (SearchCommand) -> Unit
    ): Observable<SearchAction> {
        return actions
            .debounce(400, TimeUnit.MILLISECONDS)
            .withLatestFrom(state, productsObs) { action, currentState, products -> Triple(action, currentState, products) }
            .switchMap { Observable.just(it) }
            .map { (action, currentState, products) ->
                Log.d("FEO510", "FROM bindSearchQueryChanged")
                getFilteredProducts(products, action.query, currentState.filters, currentState.sortType) to currentState
            }
            .map<SearchAction> { (products, currentState) ->
                SearchAction.RefreshData(products, currentState.sortType , currentState.filters)
            }
            .doOnNext { requestAction(it) }
    }

    private fun bindSortConfirmed(
        actions: Observable<SearchAction.SortConfirmed>,
        state: Observable<SearchState>,
        productsObs: Observable<List<Product>>,
        requestAction: (SearchAction) -> Unit,
        requestCommand: (SearchCommand) -> Unit
    ): Observable<SearchAction> {
        return actions
            .withLatestFrom(state, productsObs) { action, currentState, products -> Triple(action, currentState, products) }
            .map { (action, currentState, products) ->
                Log.d("FEO510", "FROM bindSortConfirmed")
                getFilteredProducts(products, currentState.searchQuery, currentState.filters, currentState.sortType.confirmSelection()) to currentState
            }
            .map<SearchAction> { (products, currentState) ->
                SearchAction.RefreshData(products, currentState.sortType , currentState.filters)
            }
            .doOnNext { requestAction(it) }
    }

    private fun bindFilterConfirmed(
        actions: Observable<SearchAction.SortConfirmed>,
        state: Observable<SearchState>,
        productsObs: Observable<List<Product>>,
        requestAction: (SearchAction) -> Unit,
        requestCommand: (SearchCommand) -> Unit
    ): Observable<SearchAction> {
        return actions
            .withLatestFrom(state, productsObs) { action, currentState, products -> Triple(action, currentState, products) }
            .map { (action, currentState, products) ->
                Log.d("FEO510", "FROM bindFilterConfirmed")
                getFilteredProducts(products, currentState.searchQuery, currentState.filters.confirmSelection(), currentState.sortType) to currentState
            }
            .map<SearchAction> { (products, currentState) ->
                SearchAction.RefreshData(products, currentState.sortType , currentState.filters)
            }
            .doOnNext { requestAction(it) }
    }

    private fun bindToggleFavourite(
        actions: Observable<SearchAction.ToggleFavouriteClicked>,
        state: Observable<SearchState>,
        requestAction: (SearchAction) -> Unit,
        requestCommand: (SearchCommand) -> Unit
    ): Observable<SearchAction> {
        return actions
            .flatMap { action ->
                productsRepository.toggleFavourite(action.product).toObservable()
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
        requestAction: (SearchAction) -> Unit
    ): Observable<SearchAction> {
        // 1. todo need to add these calls to disposable somehow
        // 2. todo need to cancel previous request (by using switchMap?)
        // see more info here
        // https://blog.mindorks.com/implement-search-using-rxjava-operators-c8882b64fe1d
        val filterRequirements = getFilterRequirements(searchQuery, filters)

        Log.d("FEO120", "getProductsToShow $filters")

        return productsRepository.getProducts()
            .toObservable()
            .map { it.filter { product -> filterRequirements.all { predicate -> predicate(product) } } }
            .map { it.sortedWith(sortType.type.applied.comparator) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map<SearchAction> {
                SearchAction.RefreshData(it, sortType, filters)
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

    private fun getFilteredProducts(products: List<Product>,
                                    searchQuery: String,
                                    filters: Filters,
                                    sortType: SortType
    ): List<Product> {
        Log.d("FEO510", "SORTED with ${sortType.type.applied}")
        return products
            //.filter { product -> getFilterRequirements(searchQuery, filters).all { predicate -> predicate(product) } }
            .sortedWith(sortType.type.applied.comparator)
    }

}