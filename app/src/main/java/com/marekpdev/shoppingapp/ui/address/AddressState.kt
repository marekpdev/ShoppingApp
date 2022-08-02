package com.marekpdev.shoppingapp.ui.address

import com.marekpdev.shoppingapp.models.Address
import com.marekpdev.shoppingapp.mvi.State

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
data class AddressState(
    val mode: Mode,
    val loading: Boolean,
    val addressEdited: Address?,
    val line1: String,
    val line2: String,
    val postcode: String,
    val city: String,
    val country: String,
): State

enum class Mode {
    ADD, UPDATE
}