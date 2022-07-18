package com.marekpdev.shoppingapp.ui.search

import com.marekpdev.shoppingapp.models.Color
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.models.Size
import com.marekpdev.shoppingapp.mvi.Action
import com.marekpdev.shoppingapp.repository.Menu
import com.marekpdev.shoppingapp.ui.search.filter.Filters
import com.marekpdev.shoppingapp.ui.search.sort.SortType
import java.util.*

/**
 * Created by Marek Pszczolka on 04/06/2022.
 */
sealed class SearchAction : Action {

    //object FetchInitialData: SearchAction()
    data class InitFilters(val filters: Filters): SearchAction()

    class SearchQueryChanged(val query: String): SearchAction()
    object Loading: SearchAction()
    data class InitialDataFetched(val menu: Menu, val sortType: SortType, val filters: Filters?): SearchAction()
    data class RefreshData(val menu: Menu, val displayStates: List<DisplayState>, val sortType: SortType, val filters: Filters?): SearchAction()
    class SearchError(val error: Throwable?): SearchAction()

    class ProductClicked(val productId: Long): SearchAction()
    class CategoryClicked(val categoryId: Int): SearchAction()
    object BackPressed: SearchAction()
    class ToggleFavouriteClicked(val product: Product): SearchAction()

    object SortClicked: SearchAction()
    object SortConfirmed: SearchAction()

    data class SortSelectedType(val sortType: SortType.Type): SearchAction()

    object FilterClicked: SearchAction()
    object FilterConfirmed: SearchAction()

    data class FilterSelectedPriceRangeChanged(val selectedPriceRange: IntRange): SearchAction()
    data class FilterSelectedColorChanged(val selectedColor: Color): SearchAction()
    data class FilterSelectedSizeChanged(val selectedSize: Size): SearchAction()

}