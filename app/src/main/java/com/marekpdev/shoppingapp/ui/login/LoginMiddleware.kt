package com.marekpdev.shoppingapp.ui.login

import android.util.Log
import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.repository.user.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
class LoginMiddleware @Inject constructor(private val userRepository: UserRepository) :
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
            is LoginAction.RequestLogin -> onLoginRequested(action, currentState, requestAction, requestCommand)
            else -> {}
        }
    }

    private suspend fun onLoginRequested(
        action: LoginAction.RequestLogin,
        currentState: LoginState,
        requestAction: suspend (LoginAction) -> Unit,
        requestCommand: suspend (LoginCommand) -> Unit
    ) {
        requestAction(LoginAction.Loading)
        val loginSuccess = userRepository.loginUser(action.email, action.password)
        Log.d("FEO33", "Login success ? $loginSuccess")
        if(loginSuccess) {
            requestAction(LoginAction.LoginSuccessful)
        } else {
            requestAction(LoginAction.LoginError("Wrong email or password"))
        }
    }
}