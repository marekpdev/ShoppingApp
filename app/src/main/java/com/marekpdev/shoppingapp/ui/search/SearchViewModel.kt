package com.marekpdev.shoppingapp.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hadilq.liveevent.LiveEvent
import com.marekpdev.shoppingapp.extensions.asLiveData

/**
 * Created by Marek Pszczolka on 13/03/2022.
 */
class SearchViewModel: ViewModel() {

    private val store = SearchStore(
        SearchViewState("", false, "", emptyList()),
        listOf(SearchMiddleware()),
        SearchReducer()
    )

    private val _viewState = MutableLiveData<SearchViewState>()
    val viewState: LiveData<SearchViewState>
        get() = _viewState

    private val _commands = LiveEvent<Command>()
    val commands = _commands.asLiveData()

    init {

    }

    override fun onCleared() {
        super.onCleared()
    }

    fun dispatch(action: SearchAction){
        store.dispatch(action)
    }
}