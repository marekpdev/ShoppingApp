package com.marekpdev.shoppingapp.ui.base

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.marekpdev.shoppingapp.mvi.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by Marek Pszczolka on 05/06/2022.
 */
open class BaseViewModel <S: State, A: Action, C: Command>(protected val store: Store<S, A, C>): ViewModel() {

    fun dispatch(action: A){
        viewModelScope.launch {
            store.dispatch(action)
        }
    }

    fun bind(
        owner: LifecycleOwner,
        mviView: MviView<S, C>
    ) {
        // TODO for some reason we when we put 2x or more collectLatest (or generally any other coroutine?)
        // in one launchWhenStarted then only the first one is working - not sure why
        // https://stackoverflow.com/questions/67861592/calling-multiple-viewmodel-methods-from-launchwhenstarted-does-not-work
        owner.lifecycleScope.launchWhenStarted {
            withContext(Dispatchers.Main) {
                store.state.collectLatest { mviView.render(it) }
            }
        }
        owner.lifecycleScope.launchWhenStarted {
            withContext(Dispatchers.Main) {
                // TODO might need to change collectLatest to collect because we are interested in all
                // commands and not only the latest one
                store.commands.collectLatest { mviView.onCommand(it) }
            }
        }
    }

    open fun canHandleBackPressed(): Boolean = false

}

