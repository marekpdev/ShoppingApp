package com.marekpdev.shoppingapp.di

import com.marekpdev.shoppingapp.repository.products.ProductsRepository
import com.marekpdev.shoppingapp.repository.user.UserRepository
import com.marekpdev.shoppingapp.ui.favourite.*
import com.marekpdev.shoppingapp.ui.login.*
import com.marekpdev.shoppingapp.ui.search.*
import com.marekpdev.shoppingapp.ui.search.filter.Filters
import com.marekpdev.shoppingapp.ui.search.sort.SortType
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Marek Pszczolka on 24/06/2022.
 */
@Module
@InstallIn(SingletonComponent::class)
class LoginModule {

    @Provides
    @Singleton
    fun provideLoginMiddleware(userRepository: UserRepository) = LoginMiddleware(userRepository)

    @Provides
    @Singleton
    fun provideLoginNavigationMiddleware() = LoginNavigationMiddleware()

    @Provides
    @Singleton
    fun provideLoginStore(
        loginMiddleware: LoginMiddleware,
        loginNavigationMiddleware: LoginNavigationMiddleware
    ): LoginStore {
        return LoginStore(
            LoginState("", "", false, ""),
            listOf(
                loginMiddleware,
                loginNavigationMiddleware
            ),
            LoginReducer()
        )
    }

}