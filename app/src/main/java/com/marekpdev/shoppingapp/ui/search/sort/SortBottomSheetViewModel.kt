package com.marekpdev.shoppingapp.ui.search.sort

import com.marekpdev.shoppingapp.mvi.*
import com.marekpdev.shoppingapp.ui.search.SearchAction
import com.marekpdev.shoppingapp.ui.search.SearchCommand
import com.marekpdev.shoppingapp.ui.search.SearchState
import com.marekpdev.shoppingapp.ui.search.SearchStore
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 13/03/2022.
 */

class SortBottomSheetViewModel @Inject constructor(searchStore: SearchStore): BaseViewModel<SearchState, SearchAction, SearchCommand>(searchStore)