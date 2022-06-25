package com.marekpdev.shoppingapp.ui.search

import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.mvi.State
import com.marekpdev.shoppingapp.ui.search.filter.Filters
import com.marekpdev.shoppingapp.ui.search.sort.SortType

/**
 * Created by Marek Pszczolka on 04/06/2022.
 */
data class SearchState(
    val searchQuery: String,
    val searchInProgress: Boolean,
    val searchSummary: String,
    val products: List<Product>,
    val sortType: SortType,
    val filters: Filters
): State


