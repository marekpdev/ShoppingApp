package com.marekpdev.shoppingapp.ui.search

import com.marekpdev.shoppingapp.mvi.*
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 13/03/2022.
 */

// todo use DI
class SearchViewModel @Inject constructor(): BaseViewModel<SearchState, SearchAction, SearchCommand>(SearchStore.getInstance())