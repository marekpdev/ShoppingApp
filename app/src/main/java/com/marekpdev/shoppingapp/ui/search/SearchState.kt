package com.marekpdev.shoppingapp.ui.search

import com.marekpdev.shoppingapp.mvi.State
import com.marekpdev.shoppingapp.repository.Menu
import com.marekpdev.shoppingapp.ui.search.filter.Filters
import com.marekpdev.shoppingapp.ui.search.sort.SortType
import java.util.*

/**
 * Created by Marek Pszczolka on 04/06/2022.
 */
data class SearchState(
    val searchQuery: String,
    val searchInProgress: Boolean,
    val searchSummary: String,
    val menu: Menu,
    val displayStates: List<DisplayState>, // TODO use stack? also change name to displayStatesBackstack?
    val sortType: SortType,
    val filters: Filters?,
): State

sealed class DisplayState {

    data class Category(val categoryId: Int): DisplayState()
    object SearchResults : DisplayState()

}
