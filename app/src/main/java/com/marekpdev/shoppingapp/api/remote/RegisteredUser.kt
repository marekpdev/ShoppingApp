package com.marekpdev.shoppingapp.api.remote

/**
 * Created by Marek Pszczolka on 07/05/2022.
 */
data class RegisteredUser (
    val id: Long,
    val email: String,
    val password: String,
    val name: String,
    val surname: String,
)