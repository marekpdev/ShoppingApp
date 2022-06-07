package com.marekpdev.shoppingapp.ui.search

import android.util.Log
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.repository.Data
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Created by Marek Pszczolka on 05/06/2022.
 */
class SortMiddleware: Middleware<SearchState, SearchAction, SearchCommand> {

    override fun process(
        action: SearchAction,
        currentState: SearchState,
        requestAction: (SearchAction) -> Unit,
        requestCommand: (SearchCommand) -> Unit
    ) {
        when(action){
            is SearchAction.SelectSortType -> {
                processSelectSortType(action, requestAction)
            }
            else -> {

            }
        }
    }

    private fun processSelectSortType(
        action: SearchAction.SelectSortType,
        requestAction: (SearchAction) -> Unit
    ) {

    }


}