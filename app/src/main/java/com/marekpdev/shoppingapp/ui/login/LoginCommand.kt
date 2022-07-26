package com.marekpdev.shoppingapp.ui.login

import com.marekpdev.shoppingapp.mvi.Command

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
sealed class LoginCommand: Command {

    object GoToAccountScreen: LoginCommand()
    object GoToRegistrationScreen: LoginCommand()
}