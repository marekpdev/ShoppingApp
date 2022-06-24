package com.marekpdev.shoppingapp.user

import com.marekpdev.shoppingapp.models.User

/**
 * Created by Marek Pszczolka on 13/07/2021.
 */
interface UserManager {

    var user: User?

    fun isUserLoggedIn(): Boolean

}