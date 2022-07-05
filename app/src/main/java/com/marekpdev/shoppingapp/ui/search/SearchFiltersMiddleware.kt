package com.marekpdev.shoppingapp.ui.search

import android.util.Log
import com.marekpdev.shoppingapp.models.Color
import com.marekpdev.shoppingapp.models.Size
import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.repository.Data
import com.marekpdev.shoppingapp.ui.search.filter.Filters
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.ofType
import kotlin.math.ceil
import kotlin.math.max
import kotlin.math.min

/**
 * Created by Marek Pszczolka on 05/06/2022.
 */
class SearchFiltersMiddleware: Middleware<SearchState, SearchAction, SearchCommand> {

    private val allProducts = Data.getMenu().second!!

    override fun bind(
        actions: Observable<SearchAction>,
        state: Observable<SearchState>,
        requestAction: (SearchAction) -> Unit,
        requestCommand: (SearchCommand) -> Unit
    ): Observable<SearchAction> {
        return actions.publish { shared ->
            bindInitialDataFetched(shared.ofType(), state, requestAction, requestCommand)
        }
    }

    private fun bindInitialDataFetched(
        actions: Observable<SearchAction.InitialDataFetched>,
        state: Observable<SearchState>,
        requestAction: (SearchAction) -> Unit,
        requestCommand: (SearchCommand) -> Unit
    ): Observable<SearchAction> {
        return actions.map<SearchAction> {
            getInitFiltersAction()
        }.doOnNext {
            requestAction(it)
        }
    }

    private fun getInitFiltersAction(): SearchAction.InitFilters{
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

        val availableColorsList = availableColors.toList()
        val availableSizesList = availableSizes.toList()
        val availablePriceRange = IntRange(minPrice.toInt(), ceil(maxPrice).toInt())

        val initFilters = Filters(
            colors = IntermediateSelection(availableColorsList, availableColorsList, availableColorsList),
            sizes = IntermediateSelection(availableSizesList, availableSizesList, availableSizesList),
            priceRange = IntermediateSelection(availablePriceRange, availablePriceRange, availablePriceRange)
        )

        Log.d("FEO99", "Init filters $initFilters")

        return SearchAction.InitFilters(initFilters)
    }

}