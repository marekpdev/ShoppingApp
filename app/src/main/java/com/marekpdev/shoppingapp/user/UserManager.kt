package com.marekpdev.shoppingapp.user

/**
 * Created by Marek Pszczolka on 13/07/2021.
 */
interface UserManager {

    val username: String

    fun isUserLoggedIn()

    fun isUserRegistered()
}