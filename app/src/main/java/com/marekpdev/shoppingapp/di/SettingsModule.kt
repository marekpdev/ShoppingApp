package com.marekpdev.shoppingapp.di

import com.marekpdev.shoppingapp.repository.addresses.AddressesRepository
import com.marekpdev.shoppingapp.repository.addresses.AddressesRepositoryImpl
import com.marekpdev.shoppingapp.repository.basket.BasketRepository
import com.marekpdev.shoppingapp.repository.orders.OrdersRepository
import com.marekpdev.shoppingapp.repository.orders.OrdersRepositoryImpl
import com.marekpdev.shoppingapp.repository.paymentmethods.PaymentMethodsRepository
import com.marekpdev.shoppingapp.repository.paymentmethods.PaymentMethodsRepositoryImpl
import com.marekpdev.shoppingapp.repository.products.ProductsRepositoryImpl
import com.marekpdev.shoppingapp.repository.products.ProductsRepository
import com.marekpdev.shoppingapp.repository.settings.SettingsRepository
import com.marekpdev.shoppingapp.repository.settings.SettingsRepositoryImpl
import com.marekpdev.shoppingapp.repository.user.UserRepository
import com.marekpdev.shoppingapp.ui.addresses.*
import com.marekpdev.shoppingapp.ui.orders.*
import com.marekpdev.shoppingapp.ui.paymentmethods.*
import com.marekpdev.shoppingapp.ui.product.ProductMiddleware
import com.marekpdev.shoppingapp.ui.product.ProductReducer
import com.marekpdev.shoppingapp.ui.product.ProductState
import com.marekpdev.shoppingapp.ui.product.ProductStore
import com.marekpdev.shoppingapp.ui.search.*
import com.marekpdev.shoppingapp.ui.search.filter.Filters
import com.marekpdev.shoppingapp.ui.search.sort.SortType
import com.marekpdev.shoppingapp.ui.settings.*
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
object SettingsModule {

    @Provides
    @Singleton
    fun provideSettingsMiddleware(settingsRepository: SettingsRepository) = SettingsMiddleware(settingsRepository)

    @Provides
    @Singleton
    fun provideSettingsNavigationMiddleware() = SettingsNavigationMiddleware()

    @Provides
    @Singleton
    fun provideSettingsStore(
        settingsMiddleware: SettingsMiddleware,
        settingsNavigationMiddleware: SettingsNavigationMiddleware
    ): SettingsStore {
        return SettingsStore(
            SettingsState(emptyList(), false),
            listOf(
                settingsMiddleware,
                settingsNavigationMiddleware
            ),
            SettingsReducer()
        )
    }

}

@Module
@InstallIn(SingletonComponent::class)
interface SettingsDependencyBinder {

    @Binds
    @Singleton
    fun bindSettingsRepository(repositoryImpl: SettingsRepositoryImpl): SettingsRepository

}

