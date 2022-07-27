package com.marekpdev.shoppingapp.ui.settings

import com.marekpdev.shoppingapp.mvi.Middleware
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
class SettingsNavigationMiddleware @Inject constructor() :
    Middleware<SettingsState, SettingsAction, SettingsCommand> {

    override suspend fun bind(
        coroutineScope: CoroutineScope,
        state: StateFlow<SettingsState>,
        requestAction: suspend (SettingsAction) -> Unit
    ) {

    }

    override suspend fun process(
        action: SettingsAction,
        currentState: SettingsState,
        requestAction: suspend (SettingsAction) -> Unit,
        requestCommand: suspend (SettingsCommand) -> Unit
    ) {

    }
}