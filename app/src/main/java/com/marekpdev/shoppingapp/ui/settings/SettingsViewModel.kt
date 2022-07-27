package com.marekpdev.shoppingapp.ui.settings

import com.marekpdev.shoppingapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 13/03/2022.
 */

@HiltViewModel
class SettingsViewModel @Inject constructor(settingsStore: SettingsStore): BaseViewModel<SettingsState, SettingsAction, SettingsCommand>(settingsStore)