package com.marekpdev.shoppingapp.ui.address

import com.marekpdev.shoppingapp.mvi.Reducer

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
class AddressReducer: Reducer<AddressState, AddressAction> {

    override fun reduce(currentState: AddressState, action: AddressAction): AddressState {
        return when (action){
            is AddressAction.Initialize -> {
                currentState.copy(
                    mode = action.mode,
                    line1 = "",
                    line2 = "",
                    postcode = "",
                    city = "",
                    country = ""
                )
            }
            is AddressAction.OnContentChanged -> {
                currentState.copy(
                    line1 = action.line1,
                    line2 = action.line2,
                    postcode = action.postcode,
                    city = action.city,
                    country = action.country
                )
            }
            is AddressAction.Loading -> {
                currentState.copy(loading = true)
            }
            is AddressAction.AddressFetched -> {
                val address = action.address
                currentState.copy(
                    loading = false,
                    addressEdited = address,
                    line1 = address.line1,
                    line2 = address.line2,
                    postcode = address.postcode,
                    city = address.city,
                    country = address.country
                )
            }
            is AddressAction.AddressAdded -> {
                currentState.copy(
                    loading = false
                )
            }
            is AddressAction.AddressUpdated -> {
                currentState.copy(
                    loading = false
                )
            }
            else -> {
                currentState
            }
        }
    }

}