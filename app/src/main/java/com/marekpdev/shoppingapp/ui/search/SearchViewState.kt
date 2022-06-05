package com.marekpdev.shoppingapp.ui.search

import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.mvi.State

/**
 * Created by Marek Pszczolka on 04/06/2022.
 */
data class SearchViewState(
    val searchQuery: String,
    val searchInProgress: Boolean,
    val searchSummary: String,
    val products: List<Product>
): State


