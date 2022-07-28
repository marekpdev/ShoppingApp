package com.marekpdev.shoppingapp.ui.address

import com.marekpdev.shoppingapp.models.Address
import com.marekpdev.shoppingapp.mvi.Action

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
sealed class AddressAction : Action {

    data class FetchAddress(val addressId: Long): AddressAction()
    data class InitializeMode(val mode: Mode): AddressAction()
    object Loading: AddressAction()

    data class AddressFetched(val address: Address): AddressAction()

    object AddAddress: AddressAction()
    object UpdateAddress: AddressAction()

    object AddressAdded: AddressAction()
    object AddressUpdated: AddressAction()

}