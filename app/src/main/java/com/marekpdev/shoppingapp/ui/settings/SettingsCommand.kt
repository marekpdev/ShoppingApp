package com.marekpdev.shoppingapp.ui.settings

import com.marekpdev.shoppingapp.mvi.Command

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
sealed class SettingsCommand: Command {

    object GoBackToAccountScreen: SettingsCommand()
}