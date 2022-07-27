package com.marekpdev.shoppingapp.ui.settings

import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.mvi.Store

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
class SettingsStore (
    initialState: SettingsState,
    middlewares: List<Middleware<SettingsState, SettingsAction, SettingsCommand>>,
    reducer: SettingsReducer
) : Store<SettingsState, SettingsAction, SettingsCommand>(initialState, middlewares, reducer)