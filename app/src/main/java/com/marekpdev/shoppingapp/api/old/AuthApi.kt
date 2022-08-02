package com.marekpdev.shoppingapp.api.old

import com.marekpdev.shoppingapp.domain.LoginUserResponse
import com.marekpdev.shoppingapp.domain.LogoutUserResponse
import com.marekpdev.shoppingapp.domain.RegisterUserResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

/**
 * Created by Marek Pszczolka on 10/07/2021.
 */
interface AuthApi {

    @GET("user/register")
    fun registerUser(email: String, password: String): Single<RegisterUserResponse>

    @GET("user/login")
    fun loginUser(email: String, password: String): Single<LoginUserResponse>

    @GET("user/logout")
    fun logoutUser(userId: Long): Single<LogoutUserResponse>
}