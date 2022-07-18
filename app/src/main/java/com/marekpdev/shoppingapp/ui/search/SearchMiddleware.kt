package com.marekpdev.shoppingapp.ui.search

import android.util.Log
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.repository.products.ProductsRepository
import com.marekpdev.shoppingapp.ui.search.filter.Filters
import com.marekpdev.shoppingapp.ui.search.sort.SortType
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
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
                    Log.d("FEO900", "Current state ${currentState.sortType}")
                    val allMenu = productsRepository.getAllMenu().value
                    val products = allMenu.products
                    val filteredProducts = getFilteredProducts(products, action.query, currentState.filters, currentState.sortType)
                    Log.d("FEO150", "COLLECTING")
                    val newMenu = allMenu.copy(products = filteredProducts)
                    requestAction(SearchAction.RefreshData(newMenu, currentState.sortType , currentState.filters))
                }
        }
        Log.d("FEO610", "Binding SearchMiddleware 2")
        coroutineScope.launch {
            productsRepository.getAllMenu()
                .collectLatest { menu ->
                    Log.d("FEO610", "Mapping productsFlow 1")
                    val currentState = state.value
                    val filteredProducts = getFilteredProducts(menu.products, currentState.searchQuery, currentState.filters, currentState.sortType)
                    requestAction(SearchAction.RefreshData(menu.copy(products = filteredProducts), currentState.sortType , currentState.filters))
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
        val menu = productsRepository.getAllMenu().value
        val products = menu.products
        val confirmedSortType = currentState.sortType.confirmSelection()
        val filteredProducts = getFilteredProducts(products, currentState.searchQuery, currentState.filters, confirmedSortType)
        requestAction(SearchAction.RefreshData(menu.copy(products = filteredProducts), confirmedSortType , currentState.filters))
    }

    private suspend fun onFilterConfirmed(
        action: SearchAction.FilterConfirmed,
        currentState: SearchState,
        requestAction: suspend (SearchAction) -> Unit,
        requestCommand: suspend (SearchCommand) -> Unit
    ) {
        val menu = productsRepository.getAllMenu().value
        val products = menu.products
        val confirmedFilters = currentState.filters?.confirmSelection()
        val filteredProducts = getFilteredProducts(products, currentState.searchQuery, confirmedFilters, currentState.sortType)
        requestAction(SearchAction.RefreshData(menu.copy(products = filteredProducts), currentState.sortType , confirmedFilters))
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