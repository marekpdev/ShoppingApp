package com.marekpdev.shoppingapp.di

import com.marekpdev.shoppingapp.repository.user.UserRepository
import com.marekpdev.shoppingapp.repository.user.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Marek Pszczolka on 12/07/2021.
 */

@Module
@InstallIn(SingletonComponent::class)
object UserModule {

}

@Module
@InstallIn(SingletonComponent::class)
interface UserDependencyBinder {

    @Binds
    @Singleton
    fun bindUserRepository(repositoryImpl: UserRepositoryImpl): UserRepository

}