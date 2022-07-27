package com.marekpdev.shoppingapp.ui.login

import com.marekpdev.shoppingapp.mvi.Middleware
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
class LoginNavigationMiddleware @Inject constructor() :
    Middleware<LoginState, LoginAction, LoginCommand> {

    override suspend fun bind(
        coroutineScope: CoroutineScope,
        state: StateFlow<LoginState>,
        requestAction: suspend (LoginAction) -> Unit
    ) {

    }

    override suspend fun process(
        action: LoginAction,
        currentState: LoginState,
        requestAction: suspend (LoginAction) -> Unit,
        requestCommand: suspend (LoginCommand) -> Unit
    ) {
        when(action){
            is LoginAction.RegisterClicked -> requestCommand(LoginCommand.GoToRegistrationScreen)
            is LoginAction.LoginSuccessful -> requestCommand(LoginCommand.GoToAccountScreen)
            else -> {}
        }
    }
}