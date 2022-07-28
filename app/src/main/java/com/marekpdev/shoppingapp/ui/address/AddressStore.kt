package com.marekpdev.shoppingapp.ui.address

import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.mvi.Store

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
class AddressStore (
    initialState: AddressState,
    middlewares: List<Middleware<AddressState, AddressAction, AddressCommand>>,
    reducer: AddressReducer
) : Store<AddressState, AddressAction, AddressCommand>(initialState, middlewares, reducer)