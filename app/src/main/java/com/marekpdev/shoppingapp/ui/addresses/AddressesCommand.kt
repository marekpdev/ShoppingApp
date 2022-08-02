package com.marekpdev.shoppingapp.ui.addresses

import com.marekpdev.shoppingapp.models.Address
import com.marekpdev.shoppingapp.mvi.Command

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
sealed class AddressesCommand: Command {

    object GoBackToAccountScreen: AddressesCommand()
    data class GoToAddressDetails(val address: Address): AddressesCommand()
    object GoToAddAddressScreen: AddressesCommand()
}