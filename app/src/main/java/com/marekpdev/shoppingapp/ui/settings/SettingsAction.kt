package com.marekpdev.shoppingapp.ui.settings

import com.marekpdev.shoppingapp.models.Address
import com.marekpdev.shoppingapp.models.Setting
import com.marekpdev.shoppingapp.models.User
import com.marekpdev.shoppingapp.models.order.Order
import com.marekpdev.shoppingapp.mvi.Action
import com.marekpdev.shoppingapp.ui.login.LoginAction

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
sealed class SettingsAction : Action {

    object Loading: SettingsAction()

    data class RefreshData(val settings: List<Setting>): SettingsAction()

    data class UpdateSetting(val setting: Setting): SettingsAction()

}