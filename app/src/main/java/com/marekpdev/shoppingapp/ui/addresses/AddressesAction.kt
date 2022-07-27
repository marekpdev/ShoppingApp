package com.marekpdev.shoppingapp.ui.addresses

import com.marekpdev.shoppingapp.models.Address
import com.marekpdev.shoppingapp.models.User
import com.marekpdev.shoppingapp.models.order.Order
import com.marekpdev.shoppingapp.mvi.Action
import com.marekpdev.shoppingapp.ui.login.LoginAction

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
sealed class AddressesAction : Action {

    object Loading: AddressesAction()

    data class RefreshData(val addresses: List<Address>): AddressesAction()

    data class AddressClicked(val address: Address): AddressesAction()

}