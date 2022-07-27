package com.marekpdev.shoppingapp.ui.settings

import com.marekpdev.shoppingapp.models.Address
import com.marekpdev.shoppingapp.models.Setting
import com.marekpdev.shoppingapp.models.order.Order
import com.marekpdev.shoppingapp.mvi.State

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
data class SettingsState(
    val settings: List<Setting>,
    val loading: Boolean
): State