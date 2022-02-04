package com.marekpdev.shoppingapp.user

import com.marekpdev.shoppingapp.storage.Storage
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Marek Pszczolka on 13/07/2021.
 *
 * TODO implementation from android-dagger-solution
 * (https://developer.android.com/codelabs/android-dagger#6)
 */
@Singleton
class UserManagerImpl @Inject constructor(private val storage: Storage): UserManager {
    override val username: String
        get() = "myusername"

    override fun isUserLoggedIn() {

    }

    override fun isUserRegistered() {

    }


}