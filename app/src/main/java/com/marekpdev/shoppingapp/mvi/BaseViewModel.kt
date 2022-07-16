package com.marekpdev.shoppingapp.mvi

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by Marek Pszczolka on 05/06/2022.
 */
open class BaseViewModel <S: State, A: Action, C: Command>(private val store: Store<S, A, C>): ViewModel() {

    fun dispatch(action: A){
        viewModelScope.launch {
            store.dispatch(action)
        }
    }

    fun bind(
        owner: LifecycleOwner,
        mviView: MviView<S, C>
    ) {
        owner.lifecycleScope.launchWhenStarted {
            withContext(Dispatchers.Main) {
                store.state.collectLatest { mviView.render(it) }
                store.commands.collectLatest { mviView.onCommand(it) }
            }
        }
    }

}

