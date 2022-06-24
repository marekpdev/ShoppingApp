package com.marekpdev.shoppingapp.domain

import com.marekpdev.shoppingapp.models.User

/**
 * Created by Marek Pszczolka on 07/05/2022.
 */
data class LoginUserResponse(val user: User?)
data class LogoutUserResponse(val success: Boolean, val errorMessage: String)
data class RegisterUserResponse(val success: Boolean, val errorMessage: String)