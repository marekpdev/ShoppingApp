package com.marekpdev.shoppingapp.ui.addresses

import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.mvi.Store

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
class AddressesStore (
    initialState: AddressesState,
    middlewares: List<Middleware<AddressesState, AddressesAction, AddressesCommand>>,
    reducer: AddressesReducer
) : Store<AddressesState, AddressesAction, AddressesCommand>(initialState, middlewares, reducer)