package com.marekpdev.shoppingapp.ui.settings

import com.marekpdev.shoppingapp.mvi.Reducer

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
class SettingsReducer: Reducer<SettingsState, SettingsAction> {

    override fun reduce(currentState: SettingsState, action: SettingsAction): SettingsState {
        return when (action){
            is SettingsAction.Loading -> {
                currentState.copy(loading = true)
            }
            is SettingsAction.RefreshData -> {
                currentState.copy(
                    settings = action.settings,
                    loading = false
                )
            }
            else -> {
                currentState
            }
        }
    }

}