package com.marekpdev.shoppingapp.ui.addresses

import com.marekpdev.shoppingapp.models.Address
import com.marekpdev.shoppingapp.models.order.Order
import com.marekpdev.shoppingapp.mvi.State

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
data class AddressesState(
    val addresses: List<Address>,
    val loading: Boolean
): State