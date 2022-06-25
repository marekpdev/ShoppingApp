package com.marekpdev.shoppingapp.ui.search

import com.marekpdev.shoppingapp.models.Color
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.models.Size
import com.marekpdev.shoppingapp.mvi.Action
import com.marekpdev.shoppingapp.ui.search.filter.Filters
import com.marekpdev.shoppingapp.ui.search.sort.SortType

/**
 * Created by Marek Pszczolka on 04/06/2022.
 */
sealed class SearchAction : Action {

    object FetchInitialData: SearchAction()
    data class InitFilters(val filters: Filters): SearchAction()

    class SearchQueryChanged(val query: String): SearchAction()
    object Loading: SearchAction()
    class SearchSuccess(val products: List<Product>): SearchAction()
    class SearchError(val error: Throwable?): SearchAction()

    class ProductClicked(val productId: Long): SearchAction()

    object SortClicked: SearchAction()
    object SortConfirmed: SearchAction()

    data class SelectSortType(val sortType: SortType): SearchAction()
    data class SelectFilters(val filters: Filters): SearchAction()

    object FilterClicked: SearchAction()
    object FilterConfirmed: SearchAction()

    data class FilterSelectedPriceRangeChanged(val selectedPriceRange: IntRange): SearchAction()
    data class FilterSelectedColorsChanged(val selectedColors: List<Color>): SearchAction()
    data class FilterSelectedSizesChanged(val selectedSizes: List<Size>): SearchAction()

}