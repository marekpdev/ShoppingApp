package com.marekpdev.shoppingapp.ui.login

import com.marekpdev.shoppingapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 13/03/2022.
 */

@HiltViewModel
class LoginViewModel @Inject constructor(loginStore: LoginStore): BaseViewModel<LoginState, LoginAction, LoginCommand>(loginStore)