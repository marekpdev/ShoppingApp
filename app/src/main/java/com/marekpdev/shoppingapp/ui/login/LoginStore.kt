package com.marekpdev.shoppingapp.ui.login

import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.mvi.Store

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
class LoginStore (
    initialState: LoginState,
    middlewares: List<Middleware<LoginState, LoginAction, LoginCommand>>,
    reducer: LoginReducer
) : Store<LoginState, LoginAction, LoginCommand>(initialState, middlewares, reducer)