package com.marekpdev.shoppingapp.di

import com.marekpdev.shoppingapp.repository.user.UserRepository
import com.marekpdev.shoppingapp.ui.editprofile.*
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
class EditProfileModule {

    @Provides
    @Singleton
    fun provideEditProfileMiddleware(userRepository: UserRepository) = EditProfileMiddleware(userRepository)

    @Provides
    @Singleton
    fun provideEditProfileNavigationMiddleware() = EditProfileNavigationMiddleware()

    @Provides
    @Singleton
    fun provideEditProfileStore(
        editProfileMiddleware: EditProfileMiddleware,
        editProfileNavigationMiddleware: EditProfileNavigationMiddleware
    ): EditProfileStore {
        return EditProfileStore(
            EditProfileState("", "", "", false),
            listOf(
                editProfileMiddleware,
                editProfileNavigationMiddleware
            ),
            EditProfileReducer()
        )
    }

}