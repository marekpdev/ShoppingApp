package com.marekpdev.shoppingapp.repository.user

import com.marekpdev.shoppingapp.api.AuthApi
import com.marekpdev.shoppingapp.domain.LoginUserResponse
import com.marekpdev.shoppingapp.domain.LogoutUserResponse
import com.marekpdev.shoppingapp.domain.RegisterUserResponse
import com.marekpdev.shoppingapp.models.User
import com.marekpdev.shoppingapp.user.UserManager
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 13/07/2021.
 */
class UserRepositoryImpl @Inject constructor(private val userManager: UserManager,
                                             private val authApi: AuthApi): UserRepository {

    override var user: User? = null

    override fun isUserLoggedIn(): Boolean {
        return user != null
    }

    override fun registerUser(email: String, password: String): Single<RegisterUserResponse> {
        return authApi.registerUser(email, password)
    }

    override fun loginUser(email: String, password: String): Single<LoginUserResponse> {
        return authApi.loginUser(email, password).map { response ->
            user = response.user
            response
        }
    }

    override fun logoutUser(userId: Long): Single<LogoutUserResponse> {
        return authApi.logoutUser(userId).map { response ->
            if(response.success) user = null
            response
        }
    }
}