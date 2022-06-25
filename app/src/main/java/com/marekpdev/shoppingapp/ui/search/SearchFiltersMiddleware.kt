package com.marekpdev.shoppingapp.ui.search

import android.util.Log
import com.marekpdev.shoppingapp.models.Color
import com.marekpdev.shoppingapp.models.Size
import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.repository.Data
import com.marekpdev.shoppingapp.ui.search.filter.Filters
import kotlin.math.ceil
import kotlin.math.max
import kotlin.math.min

/**
 * Created by Marek Pszczolka on 05/06/2022.
 */
class SearchFiltersMiddleware: Middleware<SearchState, SearchAction, SearchCommand> {

    private val allProducts = Data.getMenu().second!!

    override fun process(
        action: SearchAction,
        currentState: SearchState,
        requestAction: (SearchAction) -> Unit,
        requestCommand: (SearchCommand) -> Unit
    ) {
        when(action){
            is SearchAction.FetchInitialData -> {
                fetchInitFilters(requestAction)
            }
            else -> {

            }
        }
    }

    private fun fetchInitFilters(requestAction: (SearchAction) -> Unit) {
        val availableColors = mutableSetOf<Color>()
        val availableSizes = mutableSetOf<Size>()
        var minPrice = Double.MAX_VALUE
        var maxPrice = Double.MIN_VALUE

        allProducts.forEach {
            availableColors.addAll(it.availableColors)
            availableSizes.addAll(it.availableSizes)
            minPrice = min(minPrice, it.price)
            maxPrice = max(maxPrice, it.price)
        }

        val priceRange = IntRange(minPrice.toInt(), ceil(maxPrice).toInt())

        val initFilters = Filters(
            availableColors = availableColors.toList(),
            selectedColors = availableColors.toList(),
            availableSizes = availableSizes.toList(),
            selectedSizes = availableSizes.toList(),
            availablePriceRange = priceRange,
            selectedPriceRange = priceRange
        )

        Log.d("FEO99", "Init filters $initFilters")

        requestAction(SearchAction.InitFilters(initFilters))
    }

}