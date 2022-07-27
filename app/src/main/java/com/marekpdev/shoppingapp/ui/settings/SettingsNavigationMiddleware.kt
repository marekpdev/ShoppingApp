package com.marekpdev.shoppingapp.ui.settings

import android.util.Log
import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.repository.orders.OrdersRepository
import com.marekpdev.shoppingapp.repository.user.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
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