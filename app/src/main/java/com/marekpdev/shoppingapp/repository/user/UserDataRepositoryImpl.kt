package com.marekpdev.shoppingapp.repository.user

import com.marekpdev.shoppingapp.di.AppScope
import com.marekpdev.shoppingapp.user.UserManager
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 13/07/2021.
 */
@AppScope
class UserDataRepositoryImpl @Inject constructor(private val userManager: UserManager): UserDataRepository {

    override val username: String
        get() = userManager.username
}