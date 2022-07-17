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
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.lang.Exception
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 05/06/2022.
 */
class SearchMiddleware @Inject constructor(private val productsRepository: ProductsRepository)
    : Middleware<SearchState, SearchAction, SearchCommand> {

    private val searchQueryChangedActions = MutableSharedFlow<SearchAction.SearchQueryChanged>()

    // TODO
    // add support for error - similar to rx workflows
    // .onErrorReturn { e -> SearchAction.SearchError(e) }

    // todo
    // add background/ui thread workflow - similar to rx worfklows
    // .subscribeOn(Schedulers.io())
    //            .observeOn(AndroidSchedulers.mainThread())

    override suspend fun bind(
        coroutineScope: CoroutineScope,
        state: StateFlow<SearchState>,
        requestAction: suspend (SearchAction) -> Unit // TODO can remove 'suspend' from here?
    ) {
        Log.d("FEO610", "Binding SearchMiddleware 1")
        coroutineScope.launch {
            searchQueryChangedActions
                .debounce(400L)
                .collectLatest { action ->
                    requestAction(SearchAction.Loading)
                    delay(1000L) // TODO simulating search - can remove later on
                    Log.d("FEO150", "MAPPING")
                    val currentState = state.value
                    val products = productsRepository.productsFlow().value
                    val filteredProducts = getFilteredProducts(products, action.query, currentState.filters, currentState.sortType)
                    Log.d("FEO150", "COLLECTING")
                    requestAction(SearchAction.RefreshData(filteredProducts, currentState.sortType , currentState.filters))
                }
        }
        Log.d("FEO610", "Binding SearchMiddleware 2")
        coroutineScope.launch {
            productsRepository.productsFlow()
                .collectLatest { products ->
                    Log.d("FEO610", "Mapping productsFlow 1")
                    val currentState = state.value
                    val filteredProducts = getFilteredProducts(products, currentState.searchQuery, currentState.filters, currentState.sortType)
                    requestAction(SearchAction.RefreshData(filteredProducts, currentState.sortType , currentState.filters))
                }
        }
        Log.d("FEO610", "Binding SearchMiddleware 3")
    }

    override suspend fun process(
        action: SearchAction,
        currentState: SearchState,
        requestAction: suspend (SearchAction) -> Unit,
        requestCommand: suspend (SearchCommand) -> Unit
    ) {
        when(action){
            is SearchAction.SearchQueryChanged -> onSearchQueryChanged(action, currentState, requestAction, requestCommand)
            is SearchAction.SortConfirmed -> onSortConfirmed(action, currentState, requestAction, requestCommand)
            is SearchAction.FilterConfirmed -> onFilterConfirmed(action, currentState, requestAction, requestCommand)
            is SearchAction.ToggleFavouriteClicked -> onToggleFavourite(action, currentState, requestAction, requestCommand)
            else -> {
                Log.d("FEO800", "Action not handled in SearchMiddleware")
            }
        }
    }

    // need to sort out issue with naming bind1, bind2 etc
    // (cannot use two same methods with name bind when they have
    // 1 -> actions: Observable<SearchAction.SearchQueryChanged>
    // 2 -> actions: Observable<SearchAction.SortConfirmed>
    // as it is being detected as the same method signature


    private suspend fun onSearchQueryChanged(
        action: SearchAction.SearchQueryChanged,
        currentState: SearchState,
        requestAction: suspend (SearchAction) -> Unit,
        requestCommand: suspend (SearchCommand) -> Unit
    ) {
        Log.d("FEO800", "onSearchQueryChanged")
        searchQueryChangedActions.emit(action)
    }

    private suspend fun onSortConfirmed(
        action: SearchAction.SortConfirmed,
        currentState: SearchState,
        requestAction: suspend (SearchAction) -> Unit,
        requestCommand: suspend (SearchCommand) -> Unit
    ) {
        val products = productsRepository.productsFlow().value
        val filteredProducts = getFilteredProducts(products, currentState.searchQuery, currentState.filters, currentState.sortType.confirmSelection())
        requestAction(SearchAction.RefreshData(filteredProducts, currentState.sortType , currentState.filters))
    }

    private suspend fun onFilterConfirmed(
        action: SearchAction.FilterConfirmed,
        currentState: SearchState,
        requestAction: suspend (SearchAction) -> Unit,
        requestCommand: suspend (SearchCommand) -> Unit
    ) {
        val products = productsRepository.productsFlow().value
        val filteredProducts = getFilteredProducts(products, currentState.searchQuery, currentState.filters?.confirmSelection(), currentState.sortType)
        requestAction(SearchAction.RefreshData(filteredProducts, currentState.sortType , currentState.filters))
    }

    private suspend fun onToggleFavourite(
        action: SearchAction.ToggleFavouriteClicked,
        currentState: SearchState,
        requestAction: suspend (SearchAction) -> Unit,
        requestCommand: suspend (SearchCommand) -> Unit
    ) {
        productsRepository.toggleFavourite(action.product)
    }

    private fun getFilterRequirements(searchQuery: String, filters: Filters?) = listOf<(Product) -> Boolean>(
        { it.name.contains(searchQuery, true) },
        { filters == null || it.price.toInt() in filters.priceRange.applied},
        { filters == null || it.availableSizes.any { size -> filters.sizes.applied.contains(size) }},
        { filters == null || it.availableColors.any { color -> filters.colors.applied.contains(color) }},
    )

    private fun getFilteredProducts(products: List<Product>,
                                    searchQuery: String,
                                    filters: Filters?,
                                    sortType: SortType
    ): List<Product> {
        Log.d("FEO150", "Products ${products.size}")
        Log.d("FEO150", "SORTED with ${sortType.type.applied}")
        return products
            .filter { product -> getFilterRequirements(searchQuery, filters).all { predicate -> predicate(product) } }
            .sortedWith(sortType.type.applied.comparator)
    }

}