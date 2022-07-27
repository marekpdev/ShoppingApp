package com.marekpdev.shoppingapp.repository.user

import com.marekpdev.shoppingapp.models.User
import kotlinx.coroutines.flow.StateFlow

/**
 * Created by Marek Pszczolka on 13/07/2021.
 */
interface UserRepository {

    suspend fun isUserLoggedIn(): Boolean

    suspend fun loginUser(email: String, password: String): Boolean

    suspend fun logoutUser(userId: Long): Boolean

    suspend fun updateUserData(name: String, surname: String)

    fun getUser(): StateFlow<User?>

//    suspend fun registerUser(email: String, password: String): Boolean

}