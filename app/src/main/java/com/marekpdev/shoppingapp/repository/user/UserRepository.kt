package com.marekpdev.shoppingapp.repository.user

import com.marekpdev.shoppingapp.domain.LoginUserResponse
import com.marekpdev.shoppingapp.domain.LogoutUserResponse
import com.marekpdev.shoppingapp.domain.RegisterUserResponse
import com.marekpdev.shoppingapp.models.User
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

/**
 * Created by Marek Pszczolka on 13/07/2021.
 */
interface UserRepository {

    var user: User?

    fun isUserLoggedIn(): Boolean

    fun registerUser(email: String, password: String): Single<RegisterUserResponse>

    fun loginUser(email: String, password: String): Single<LoginUserResponse>

    fun logoutUser(userId: Long): Single<LogoutUserResponse>

}