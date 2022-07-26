package com.marekpdev.shoppingapp.ui.login

import com.marekpdev.shoppingapp.mvi.Reducer

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
class LoginReducer: Reducer<LoginState, LoginAction> {

    override fun reduce(currentState: LoginState, action: LoginAction): LoginState {
        return when (action){
            is LoginAction.InputChanged -> {
                currentState.copy(
                    email = action.email,
                    password = action.password
                )
            }
            is LoginAction.Loading -> {
                currentState.copy(loading = true)
            }
            is LoginAction.LoginError -> {
                currentState.copy(
                    error = action.error,
                    loading = false
                )
            }
            else -> {
                currentState
            }
        }
    }

}