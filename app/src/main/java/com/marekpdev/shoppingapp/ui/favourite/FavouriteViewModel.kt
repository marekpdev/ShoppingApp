package com.marekpdev.shoppingapp.ui.favourite

import com.marekpdev.shoppingapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 13/03/2022.
 */

@HiltViewModel
class FavouriteViewModel @Inject constructor(favouriteStore: FavouriteStore): BaseViewModel<FavouriteState, FavouriteAction, FavouriteCommand>(favouriteStore) {

    init {
//        favouriteStore.dispatch(FavouriteAction.FetchInitialData)
    }
}