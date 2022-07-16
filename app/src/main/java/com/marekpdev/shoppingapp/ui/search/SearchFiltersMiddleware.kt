package com.marekpdev.shoppingapp.ui.search

import android.util.Log
import com.marekpdev.shoppingapp.models.Color
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.models.Size
import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.repository.products.ProductsRepository
import com.marekpdev.shoppingapp.ui.search.filter.Filters
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import kotlin.math.ceil
import kotlin.math.max
import kotlin.math.min

/**
 * Created by Marek Pszczolka on 05/06/2022.
 */
class SearchFiltersMiddleware @Inject constructor(private val productsRepository: ProductsRepository)
    : Middleware<SearchState, SearchAction, SearchCommand> {

    override fun bind(
        actions: Observable<SearchAction>,
        state: Observable<SearchState>,
        requestAction: (SearchAction) -> Unit,
        requestCommand: (SearchCommand) -> Unit
    ): Observable<SearchAction> {
        return observeProducts(requestAction)
    }

    private fun observeProducts(
        requestAction: (SearchAction) -> Unit
    ): Observable<SearchAction> {
        return productsRepository
            .observeProducts()
            .map<SearchAction> {
                getInitFiltersAction(it)
            }
            .distinctUntilChanged()
            .doOnNext {
                Log.d("FEO420", "doOnNext")
                requestAction(it)
            }
    }

    private fun getInitFiltersAction(products: List<Product>): SearchAction.InitFilters{
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

        Log.d("FEO99", "Init filters $initFilters")

        return SearchAction.InitFilters(initFilters)
    }

}