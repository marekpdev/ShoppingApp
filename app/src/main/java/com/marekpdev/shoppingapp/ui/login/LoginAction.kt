package com.marekpdev.shoppingapp.ui.login

import com.marekpdev.shoppingapp.mvi.Action

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
sealed class LoginAction : Action {

    data class InputChanged(val email: String, val password: String): LoginAction()
    data class RequestLogin(val email: String, val password: String): LoginAction()
    data class LoginError(val error: String): LoginAction()
    object LoginSuccessful: LoginAction()
    object Loading: LoginAction()

    object RegisterClicked: LoginAction()
}