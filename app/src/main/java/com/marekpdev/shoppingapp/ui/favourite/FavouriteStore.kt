package com.marekpdev.shoppingapp.ui.favourite

import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.mvi.Store
import com.marekpdev.shoppingapp.ui.search.sort.SortType
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 04/06/2022.
 */

class FavouriteStore (
    initialState: FavouriteState,
    middlewares: List<Middleware<FavouriteState, FavouriteAction, FavouriteCommand>>,
    reducer: FavouriteReducer
) : Store<FavouriteState, FavouriteAction, FavouriteCommand>(initialState, middlewares, reducer)