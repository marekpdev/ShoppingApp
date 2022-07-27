package com.marekpdev.shoppingapp.di

import com.marekpdev.shoppingapp.repository.paymentmethods.PaymentMethodsRepository
import com.marekpdev.shoppingapp.repository.paymentmethods.PaymentMethodsRepositoryImpl
import com.marekpdev.shoppingapp.repository.user.UserRepository
import com.marekpdev.shoppingapp.ui.paymentmethods.*
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
object PaymentMethodsModule {

    @Provides
    @Singleton
    fun providePaymentMethodsMiddleware(userRepository: UserRepository, paymentMethodsRepository: PaymentMethodsRepository) = PaymentMethodsMiddleware(userRepository, paymentMethodsRepository)

    @Provides
    @Singleton
    fun providePaymentMethodsNavigationMiddleware() = PaymentMethodsNavigationMiddleware()

    @Provides
    @Singleton
    fun providePaymentMethodsStore(
        paymentMethodsMiddleware: PaymentMethodsMiddleware,
        paymentMethodsNavigationMiddleware: PaymentMethodsNavigationMiddleware
    ): PaymentMethodsStore {
        return PaymentMethodsStore(
            PaymentMethodsState(emptyList(), false),
            listOf(
                paymentMethodsMiddleware,
                paymentMethodsNavigationMiddleware
            ),
            PaymentMethodsReducer()
        )
    }

}

@Module
@InstallIn(SingletonComponent::class)
interface PaymentMethodsDependencyBinder {

    @Binds
    @Singleton
    fun bindPaymentMethodsRepository(repositoryImpl: PaymentMethodsRepositoryImpl): PaymentMethodsRepository

}

