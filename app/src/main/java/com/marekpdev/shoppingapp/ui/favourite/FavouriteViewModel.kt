package com.marekpdev.shoppingapp.ui.favourite

import android.util.Log
import com.marekpdev.shoppingapp.mvi.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 13/03/2022.
 */

@HiltViewModel
class FavouriteViewModel @Inject constructor(favouriteStore: FavouriteStore): BaseViewModel<FavouriteState, FavouriteAction, FavouriteCommand>(favouriteStore) {

    init {
        Log.d("FEO70", "init FavouriteViewModel")
        favouriteStore.dispatch(FavouriteAction.FetchInitialData)
    }
}