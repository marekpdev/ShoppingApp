package com.marekpdev.shoppingapp.ui.settings

import com.marekpdev.shoppingapp.models.Setting
import com.marekpdev.shoppingapp.mvi.Action

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
sealed class SettingsAction : Action {

    object Loading: SettingsAction()

    data class RefreshData(val settings: List<Setting>): SettingsAction()

    data class UpdateSetting(val setting: Setting): SettingsAction()

    data class ToggleNotifications(val notifications: Setting.Notifications): SettingsAction()
    data class ToggleRecommendations(val recommendations: Setting.Recommendations): SettingsAction()

}