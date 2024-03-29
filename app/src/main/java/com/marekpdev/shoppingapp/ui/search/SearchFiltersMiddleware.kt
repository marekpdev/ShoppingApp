package com.marekpdev.shoppingapp.ui.search

import com.marekpdev.shoppingapp.models.Color
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.models.Size
import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.repository.products.ProductsRepository
import com.marekpdev.shoppingapp.ui.search.filter.Filters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.ceil
import kotlin.math.max
import kotlin.math.min

/**
 * Created by Marek Pszczolka on 05/06/2022.
 */
class SearchFiltersMiddleware @Inject constructor(private val productsRepository: ProductsRepository)
    : Middleware<SearchState, SearchAction, SearchCommand> {

    override suspend fun bind(
        coroutineScope: CoroutineScope,
        state: StateFlow<SearchState>,
        requestAction: suspend (SearchAction) -> Unit
    ) {
        // TODO this only works if we add buffer() - not sure why, need to investigate
        // seems like collectLatest() internally uses buffer() as well so that's why the other solution works
        // (if we changed the other solution to just collect() it doesn't work either)
        // Also might need to change it to coroutineScope.launch(dispatcher) instead of launchIn() chain
        // so we can use custom dispatcher if needed (e.g. for testing)
        productsRepository.getAllMenu()
            .map { menu -> getInitFiltersAction(menu.products) }
            .distinctUntilChanged()
            .onEach { filters -> requestAction(SearchAction.InitFilters(filters)) }
            .buffer(0)
            .launchIn(coroutineScope)

        // TODO this worked well
//        coroutineScope.launch {
//            productsRepository.getAllMenu()
//                .map { menu -> getInitFiltersAction(menu.products) }
//                .distinctUntilChanged()
//                .collectLatest { filters -> requestAction(SearchAction.InitFilters(filters)) }
//        }
    }

    override suspend fun process(
        action: SearchAction,
        currentState: SearchState,
        requestAction: suspend (SearchAction) -> Unit,
        requestCommand: suspend (SearchCommand) -> Unit
    ) {

    }

    private fun getInitFiltersAction(products: List<Product>): Filters{
        val availableColors = mutableSetOf<Color>()
        val availableSizes = mutableSetOf<Size>()
        var minPrice = Double.MAX_VALUE
        var maxPrice = Double.MIN_VALUE

        products.forEach {
            availableColors.addAll(it.availableColors)
            availableSizes.addAll(it.availableSizes)
            minPrice = min(minPrice, it.price)
            maxPrice = max(maxPrice, it.price)
        }

        val availableColorsList = availableColors.toList()
        val availableSizesList = availableSizes.toList()
        val availablePriceRange = IntRange(minPrice.toInt(), ceil(maxPrice).toInt())

        val initFilters = Filters(
            colors = IntermediateSelection(availableColorsList, availableColorsList, availableColorsList),
            sizes = IntermediateSelection(availableSizesList, availableSizesList, availableSizesList),
            priceRange = IntermediateSelection(availablePriceRange, availablePriceRange, availablePriceRange)
        )

        return initFilters
    }

}