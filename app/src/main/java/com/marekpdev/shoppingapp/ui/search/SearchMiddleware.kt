package com.marekpdev.shoppingapp.ui.search

import android.util.Log
import com.marekpdev.shoppingapp.models.Category
import com.marekpdev.shoppingapp.models.DisplayPlace
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.models.ROOT_CATEGORY_ID
import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.repository.products.ProductsRepository
import com.marekpdev.shoppingapp.ui.search.filter.Filters
import com.marekpdev.shoppingapp.ui.search.sort.SortType
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.util.*
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
        coroutineScope.launch {
            searchQueryChangedActions
                .debounce(400L)
                .collectLatest { action ->
                    val hasSearchQuery = action.query.isNotBlank()

                    val currentState = state.value

                    if(currentState.displayStates.isEmpty()) return@collectLatest

                    val allMenu = productsRepository.getAllMenu().value

                    if(hasSearchQuery){
                        requestAction(SearchAction.Loading)
                        delay(1000L) // TODO simulating search - can remove later on

                        val filteredProducts = getFilteredProducts(allMenu.products, action.query, DisplayState.SearchResults, currentState.filters, currentState.sortType)
                        val filteredCategories = getFilteredCategories(allMenu.categories, DisplayState.SearchResults)

                        val newMenu = allMenu.copy(
                            categories = filteredCategories,
                            products = filteredProducts
                        )

                        val lastDisplayState = currentState.displayStates.last()

                        val displayStates = when(lastDisplayState is DisplayState.SearchResults) {
                            true -> currentState.displayStates // no need to add search results
                            else -> {
                                currentState.displayStates
                                    .map { it }
                                    .toMutableList()
                                    .apply { add(DisplayState.SearchResults) }
                                    .toList()
                            }
                        }

                        val searchSummary = getSearchSummary(filteredProducts, displayStates.last())

                        requestAction(SearchAction.RefreshData(newMenu, searchSummary, displayStates, currentState.sortType , currentState.filters))
                    } else {
                        val lastDisplayState = currentState.displayStates.last()

                        val displayStates = when(lastDisplayState is DisplayState.SearchResults) {
                            true -> {
                                currentState.displayStates
                                    .map { it }
                                    .toMutableList()
                                    .apply { removeLast() }
                                    .toList()
                            }
                            else -> {
                                currentState.displayStates
                            }
                        }

                        val filteredProducts = getFilteredProducts(allMenu.products, action.query, displayStates.last(), currentState.filters, currentState.sortType)
                        val filteredCategories = getFilteredCategories(allMenu.categories, displayStates.last())

                        val newMenu = allMenu.copy(
                            categories = filteredCategories,
                            products = filteredProducts
                        )

                        val searchSummary = getSearchSummary(filteredProducts, displayStates.last())

                        requestAction(SearchAction.RefreshData(newMenu, searchSummary, displayStates, currentState.sortType , currentState.filters))
                    }
                }
        }

        coroutineScope.launch {
            productsRepository.getAllMenu()
                .collectLatest { allMenu ->

                    val currentState = state.value

                    if(currentState.displayStates.isEmpty()){
                        val rootCategory = allMenu.categories.find { it.isRootCategory() }
                        rootCategory?.let {

                            val displayStates = listOf(DisplayState.Category(rootCategory))

                            val filteredProducts = getFilteredProducts(allMenu.products, currentState.searchQuery, displayStates.last(), currentState.filters, currentState.sortType)
                            val filteredCategories = getFilteredCategories(allMenu.categories, displayStates.last())
                            val newMenu = allMenu.copy(
                                categories = filteredCategories,
                                products = filteredProducts
                            )
                            val searchSummary = getSearchSummary(filteredProducts, displayStates.last())
                            requestAction(SearchAction.RefreshData(newMenu, searchSummary, displayStates, currentState.sortType , currentState.filters))
                        }
                    } else {
                        val filteredProducts = getFilteredProducts(allMenu.products, currentState.searchQuery, currentState.displayStates.last(), currentState.filters, currentState.sortType)
                        val filteredCategories = getFilteredCategories(allMenu.categories, currentState.displayStates.last())
                        val newMenu = allMenu.copy(
                            categories = filteredCategories,
                            products = filteredProducts
                        )
                        val searchSummary = getSearchSummary(filteredProducts, currentState.displayStates.last())
                        requestAction(SearchAction.RefreshData(newMenu, searchSummary, currentState.displayStates, currentState.sortType , currentState.filters))
                    }
                }
        }

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
            is SearchAction.CategoryClicked -> onCategoryClicked(action, currentState, requestAction, requestCommand)
            is SearchAction.BackPressed -> onBackPressed(action, currentState, requestAction, requestCommand)
            else -> {

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
        searchQueryChangedActions.emit(action)
    }

    private suspend fun onCategoryClicked(
        action: SearchAction.CategoryClicked,
        currentState: SearchState,
        requestAction: suspend (SearchAction) -> Unit,
        requestCommand: suspend (SearchCommand) -> Unit
    ) {
        if(currentState.displayStates.isEmpty()) return
        requestAction(SearchAction.Loading)
        delay(500L) // TODO simulating search - can remove later on

        val allMenu = productsRepository.getAllMenu().value

        val displayStates = currentState.displayStates
            .map { it }
            .toMutableList()
            .apply { add(DisplayState.Category(action.category)) }
            .toList()

        val filteredProducts = getFilteredProducts(allMenu.products, currentState.searchQuery, displayStates.last(), currentState.filters, currentState.sortType)
        val filteredCategories = getFilteredCategories(allMenu.categories, displayStates.last())
        val newMenu = allMenu.copy(
            categories = filteredCategories,
            products = filteredProducts
        )
        val searchSummary = getSearchSummary(filteredProducts, displayStates.last())
        requestAction(SearchAction.RefreshData(newMenu, searchSummary, displayStates, currentState.sortType , currentState.filters))
    }

    // TODO
    // there is lots of copy/paste when passing all these parameters
    // action: SearchAction.BackPressed,
    // currentState: SearchState,
    // requestAction: suspend (SearchAction) -> Unit,
    // requestCommand: suspend (SearchCommand) -> Unit
    // need to come up with some smarter workflow
    // maybe just wrap it in data class UseCaseDependencies?
    private suspend fun onBackPressed(
        action: SearchAction.BackPressed,
        currentState: SearchState,
        requestAction: suspend (SearchAction) -> Unit,
        requestCommand: suspend (SearchCommand) -> Unit
    ) {
        if(currentState.displayStates.isEmpty()) return

        if(currentState.displayStates.size > 1){ // todo need to handle workflow properly
            requestAction(SearchAction.Loading)
            delay(500L) // TODO simulating search - can remove later on

            val displayStates = currentState.displayStates
                .map { it }
                .toMutableList()
                .apply { removeLast() }
                .toList()

            val allMenu = productsRepository.getAllMenu().value

            val filteredProducts = getFilteredProducts(allMenu.products, currentState.searchQuery, displayStates.last(), currentState.filters, currentState.sortType)
            val filteredCategories = getFilteredCategories(allMenu.categories, displayStates.last())
            val newMenu = allMenu.copy(
                categories = filteredCategories,
                products = filteredProducts
            )
            val searchSummary = getSearchSummary(filteredProducts, displayStates.last())
            requestAction(SearchAction.RefreshData(newMenu, searchSummary, displayStates, currentState.sortType , currentState.filters))
        }
    }

    private suspend fun onSortConfirmed(
        action: SearchAction.SortConfirmed,
        currentState: SearchState,
        requestAction: suspend (SearchAction) -> Unit,
        requestCommand: suspend (SearchCommand) -> Unit
    ) {

        val allMenu = productsRepository.getAllMenu().value
        val confirmedSortType = currentState.sortType.confirmSelection()
        val filteredProducts = getFilteredProducts(allMenu.products, currentState.searchQuery, currentState.displayStates.lastOrNull(), currentState.filters, confirmedSortType)
        val filteredCategories = getFilteredCategories(allMenu.categories, currentState.displayStates.lastOrNull())
        val newMenu = allMenu.copy(
            categories = filteredCategories,
            products = filteredProducts
        )
        val searchSummary = getSearchSummary(filteredProducts, currentState.displayStates.lastOrNull())
        requestAction(SearchAction.RefreshData(newMenu, searchSummary, currentState.displayStates, confirmedSortType , currentState.filters))
    }

    private suspend fun onFilterConfirmed(
        action: SearchAction.FilterConfirmed,
        currentState: SearchState,
        requestAction: suspend (SearchAction) -> Unit,
        requestCommand: suspend (SearchCommand) -> Unit
    ) {
        val allMenu = productsRepository.getAllMenu().value
        val confirmedFilters = currentState.filters?.confirmSelection()
        val filteredProducts = getFilteredProducts(allMenu.products, currentState.searchQuery, currentState.displayStates.lastOrNull(), confirmedFilters, currentState.sortType)
        val filteredCategories = getFilteredCategories(allMenu.categories, currentState.displayStates.lastOrNull())
        val newMenu = allMenu.copy(
            categories = filteredCategories,
            products = filteredProducts
        )

        val searchSummary = getSearchSummary(filteredProducts, currentState.displayStates.lastOrNull())
        requestAction(SearchAction.RefreshData(newMenu, searchSummary, currentState.displayStates, currentState.sortType , confirmedFilters))
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

    private fun getFilteredCategories(categories: List<Category>,
                                      displayState: DisplayState?): List<Category>{
        if(displayState == null) return emptyList()

        return categories
            .filter { category ->
                category.displayPlace == DisplayPlace.MENU &&
                        when (displayState) {
                            is DisplayState.Category -> displayState.category.id == category.parentCategoryId // only show categories for the given category
                            is DisplayState.SearchResults -> false // no need to show categories
                        }
            }
    }

    private fun getFilteredProducts(products: List<Product>,
                                    searchQuery: String,
                                    displayState: DisplayState?,
                                    filters: Filters?,
                                    sortType: SortType
    ): List<Product> {
        if(displayState == null) return emptyList()

        return products
            .filter { product ->
                when(displayState) {
                    is DisplayState.Category -> displayState.category.id in product.parentCategoryIds // only show products for the given category
                    else -> true // no filtering needed
                }
            }
            .filter { product -> getFilterRequirements(searchQuery, filters).all { predicate -> predicate(product) } }
            .sortedWith(sortType.type.applied.comparator)
    }

    private fun getSearchSummary(products: List<Product>, displayState: DisplayState?): String {
        if(displayState == null) return ""

        return when {
            displayState is DisplayState.SearchResults -> "Found ${products.size} products"
            displayState is DisplayState.Category && !displayState.category.isRootCategory() -> "${displayState.category.name} (${products.size})"
            else -> ""
        }
    }

}