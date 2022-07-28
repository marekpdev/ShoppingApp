package com.marekpdev.shoppingapp.ui.address

import com.marekpdev.shoppingapp.models.Address
import com.marekpdev.shoppingapp.mvi.Command

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
sealed class AddressCommand: Command {

    object GoBackToAddressesScreen: AddressCommand()
}