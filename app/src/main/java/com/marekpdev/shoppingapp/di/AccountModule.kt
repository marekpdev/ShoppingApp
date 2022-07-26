package com.marekpdev.shoppingapp.di

import com.marekpdev.shoppingapp.repository.user.UserRepository
import com.marekpdev.shoppingapp.ui.account.*
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
class AccountModule {

    @Provides
    @Singleton
    fun provideAccountMiddleware(userRepository: UserRepository) = AccountMiddleware(userRepository)

    @Provides
    @Singleton
    fun provideAccountNavigationMiddleware() = AccountNavigationMiddleware()

    @Provides
    @Singleton
    fun provideAccountStore(
        accountMiddleware: AccountMiddleware,
        accountNavigationMiddleware: AccountNavigationMiddleware
    ): AccountStore {
        return AccountStore(
            AccountState(""),
            listOf(
                accountMiddleware,
                accountNavigationMiddleware
            ),
            AccountReducer()
        )
    }

}