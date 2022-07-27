package com.marekpdev.shoppingapp.ui.settings

import android.util.Log
import com.marekpdev.shoppingapp.models.Setting
import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.repository.addresses.AddressesRepository
import com.marekpdev.shoppingapp.repository.orders.OrdersRepository
import com.marekpdev.shoppingapp.repository.settings.SettingsRepository
import com.marekpdev.shoppingapp.repository.user.UserRepository
import com.marekpdev.shoppingapp.ui.favourite.FavouriteAction
import com.marekpdev.shoppingapp.ui.favourite.FavouriteCommand
import com.marekpdev.shoppingapp.ui.favourite.FavouriteState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
class SettingsMiddleware @Inject constructor(
    private val settingsRepository: SettingsRepository
    )
    : Middleware<SettingsState, SettingsAction, SettingsCommand> {

    override suspend fun bind(
        coroutineScope: CoroutineScope,
        state: StateFlow<SettingsState>,
        requestAction: suspend (SettingsAction) -> Unit
    ) {
        coroutineScope.launch {
            requestAction(SettingsAction.Loading)
            settingsRepository.getSettings()
                .collectLatest { settings ->
                    requestAction(SettingsAction.RefreshData(settings))
                }
        }
    }

    override suspend fun process(
        action: SettingsAction,
        currentState: SettingsState,
        requestAction: suspend (SettingsAction) -> Unit,
        requestCommand: suspend (SettingsCommand) -> Unit
    ) {
        when(action) {
            is SettingsAction.ToggleNotifications -> onToggleNotifications(action, currentState, requestAction, requestCommand)
            is SettingsAction.ToggleRecommendations -> onToggleRecommendations(action, currentState, requestAction, requestCommand)
        }
    }

    private suspend fun onToggleNotifications(
        action: SettingsAction.ToggleNotifications,
        currentState: SettingsState,
        requestAction: suspend (SettingsAction) -> Unit,
        requestCommand: suspend (SettingsCommand) -> Unit
    ) {
        val oldSetting = action.notifications
        val newSetting = action.notifications.copy(enabled = !oldSetting.enabled)
        settingsRepository.updateSetting(oldSetting, newSetting)
    }

    private suspend fun onToggleRecommendations(
        action: SettingsAction.ToggleRecommendations,
        currentState: SettingsState,
        requestAction: suspend (SettingsAction) -> Unit,
        requestCommand: suspend (SettingsCommand) -> Unit
    ) {
        val oldSetting = action.recommendations
        val newSetting = action.recommendations.copy(enabled = !oldSetting.enabled)
        settingsRepository.updateSetting(oldSetting, newSetting)
    }
}