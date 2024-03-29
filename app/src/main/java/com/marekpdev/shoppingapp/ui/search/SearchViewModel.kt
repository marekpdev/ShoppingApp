package com.marekpdev.shoppingapp.ui.search

import com.marekpdev.shoppingapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 13/03/2022.
 */

@HiltViewModel
class SearchViewModel @Inject constructor(store: SearchStore): BaseViewModel<SearchState, SearchAction, SearchCommand>(store) {

    override fun canHandleBackPressed(): Boolean {
        return store.canHandleBackPressed()
    }
}