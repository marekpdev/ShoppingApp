package com.marekpdev.shoppingapp.ui.login

import com.marekpdev.shoppingapp.mvi.State

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
data class LoginState(
    val email: String,
    val password: String,
    val loading: Boolean,
    val error: String
): State