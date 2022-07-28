package com.marekpdev.shoppingapp.di

import com.marekpdev.shoppingapp.repository.addresses.AddressesRepository
import com.marekpdev.shoppingapp.repository.addresses.AddressesRepositoryImpl
import com.marekpdev.shoppingapp.repository.user.UserRepository
import com.marekpdev.shoppingapp.ui.address.*
import com.marekpdev.shoppingapp.ui.addresses.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Marek Pszczolka on 12/07/2021.
 */
@Module
@InstallIn(SingletonComponent::class)
object AddressModule {

    @Provides
    @Singleton
    fun provideAddressMiddleware(userRepository: UserRepository, addressesRepository: AddressesRepository) = AddressMiddleware(userRepository, addressesRepository)

    @Provides
    @Singleton
    fun provideAddressNavigationMiddleware() = AddressNavigationMiddleware()

    @Provides
    @Singleton
    fun provideAddressStore(
        addressMiddleware: AddressMiddleware,
        addressNavigationMiddleware: AddressNavigationMiddleware
    ): AddressStore {
        return AddressStore(
            AddressState(Mode.ADD, false, null, "", "", "", "", ""),
            listOf(
                addressMiddleware,
                addressNavigationMiddleware
            ),
            AddressReducer()
        )
    }

}

