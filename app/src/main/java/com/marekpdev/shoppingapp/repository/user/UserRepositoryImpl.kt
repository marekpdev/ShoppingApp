package com.marekpdev.shoppingapp.repository.user

import com.marekpdev.shoppingapp.models.User
import kotlinx.coroutines.delay
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 13/07/2021.
 */
class UserRepositoryImpl @Inject constructor(): UserRepository {

    // todo only for testing workflow
    private val users = listOf(User(1, "test@test.com", "password"))

    private var loggedInUser: User? = null

    override suspend fun isUserLoggedIn(): Boolean {
        return loggedInUser != null
    }

    override suspend fun loginUser(email: String, password: String): Boolean {
        delay(1000L) // todo remove - only for testing
        for(user in users){
            if(user.email == email && user.password == password) return true
        }
        return false
    }

    override suspend fun logoutUser(userId: Long): Boolean {
        delay(1000L) // todo remove - only for testing
        loggedInUser = null
        return true
    }
}