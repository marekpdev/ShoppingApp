package com.marekpdev.shoppingapp.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hadilq.liveevent.LiveEvent
import com.marekpdev.shoppingapp.extensions.asLiveData
import com.marekpdev.shoppingapp.mvi.*
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 13/03/2022.
 */

// todo
// use DI
private val store = Store(
    SearchViewState("", false, "", emptyList()),
    listOf(SearchMiddleware()),
    SearchReducer()
)

class SearchViewModel @Inject constructor(): BaseViewModel<SearchViewState, SearchAction, SearchCommand>(store), MviView<SearchViewState, SearchAction, SearchCommand> {



    init {
        compositeDisposable.addAll(
            store.wire(),
            store.bind(this::onNewState)
        )
        store.bind {  }
    }






}