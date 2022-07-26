package com.marekpdev.shoppingapp.repository.user

import com.marekpdev.shoppingapp.models.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 13/07/2021.
 */
class UserRepositoryImpl @Inject constructor(): UserRepository {

    // todo only for testing workflow
    private val users = listOf(User(1, "test@test.com", "password", "TestName", "TestSurname"))

    private var loggedInUser = MutableStateFlow<User?>(null)

    override fun getUser(): StateFlow<User?> {
        return loggedInUser
    }

    override suspend fun isUserLoggedIn(): Boolean {
        return loggedInUser.value != null
    }

    override suspend fun loginUser(email: String, password: String): Boolean {
        delay(1000L) // todo remove - only for testing
        for(user in users){
            if(user.email == email && user.password == password) {
                loggedInUser.value = user
                return true
            }
        }
        return false
    }

    override suspend fun logoutUser(userId: Long): Boolean {
        delay(1000L) // todo remove - only for testing
        loggedInUser.value = null
        return true
    }

    override suspend fun updateUserData(name: String, surname: String) {
        delay(1000L)
        loggedInUser.value?.let {
            val updatedUser = it.copy(name = name, surname = surname)
            loggedInUser.emit(updatedUser)
        }
    }
}