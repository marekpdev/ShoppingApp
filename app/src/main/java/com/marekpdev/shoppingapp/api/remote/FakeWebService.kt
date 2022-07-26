package com.marekpdev.shoppingapp.api.remote

import com.marekpdev.shoppingapp.api.AuthApi
import com.marekpdev.shoppingapp.domain.LoginUserResponse
import com.marekpdev.shoppingapp.domain.LogoutUserResponse
import com.marekpdev.shoppingapp.domain.RegisterUserResponse
import com.marekpdev.shoppingapp.models.User
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.atomic.AtomicLong

/**
 * Created by Marek Pszczolka on 07/05/2022.
 */
//class FakeWebService: AuthApi {

//    private val registeredUsers = mutableSetOf<RegisteredUser>()
//    private val loggedInUsers = mutableSetOf<RegisteredUser>()
//
//    private val atomicInt = AtomicLong()
//
//    init {
//        registeredUsers.add(
//            RegisteredUser(
//                atomicInt.getAndIncrement(),
//                    "marek@test.com",
//                    "password1",
//                    "marek",
//                    "bee"
//                )
//        )
//    }
//
//    override fun registerUser(email: String, password: String): Single<RegisterUserResponse> {
//        return Single.create { emitter ->
//            val newUser = RegisteredUser(
//                atomicInt.getAndIncrement(),
//                email,
//                password,
//                "",
//                ""
//            )
//
//            if(!registeredUsers.contains(newUser)){
//                registeredUsers.add(newUser)
//                emitter.onSuccess(RegisterUserResponse(true, "User registered"))
//            } else {
//                emitter.onSuccess(RegisterUserResponse(false, "User already exists"))
//            }
//        }
//    }
//
//    override fun loginUser(email: String, password: String): Single<LoginUserResponse> {
//        return Single.create { emitter ->
//            val user = loggedInUsers.find { it.email == email && it.password == password }
//
//            if (user != null) {
//                loggedInUsers.add(user)
//                emitter.onSuccess(
//                    LoginUserResponse(
//                        User(
//                            user.id,
//                            user.email,
//                            "",
//                            ""
//                        )
//
//                    )
//                )
//            } else {
//                emitter.onSuccess(LoginUserResponse(null))
//            }
//
//        }
//    }
//
//    override fun logoutUser(userId: Long): Single<LogoutUserResponse> {
//        return Single.create { emitter ->
//            val user = loggedInUsers.find { it.id == userId }
//
//            if (user != null && loggedInUsers.contains(user)) {
//                loggedInUsers.remove(user)
//                emitter.onSuccess(LogoutUserResponse(true, "User logged out"))
//            } else {
//                emitter.onSuccess(LogoutUserResponse(false, "User not logged in"))
//            }
//
//        }
//    }


//}