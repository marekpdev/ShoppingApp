package com.marekpdev.shoppingapp.ui.addresses

import com.marekpdev.shoppingapp.mvi.Reducer

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
class AddressesReducer: Reducer<AddressesState, AddressesAction> {

    override fun reduce(currentState: AddressesState, action: AddressesAction): AddressesState {
        return when (action){
            is AddressesAction.Loading -> {
                currentState.copy(loading = true)
            }
            is AddressesAction.RefreshData -> {
                currentState.copy(
                    addresses = action.addresses,
                    loading = false
                )
            }
            else -> {
                currentState
            }
        }
    }

}