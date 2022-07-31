package com.marekpdev.shoppingapp.di

import com.marekpdev.shoppingapp.repository.paymentmethods.PaymentMethodsRepository
import com.marekpdev.shoppingapp.repository.user.UserRepository
import com.marekpdev.shoppingapp.ui.paymentcard.*
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
object PaymentCardModule {

    @Provides
    @Singleton
    fun providePaymentCardMiddleware(userRepository: UserRepository, paymentMethodsRepository: PaymentMethodsRepository) = PaymentCardMiddleware(userRepository, paymentMethodsRepository)

    @Provides
    @Singleton
    fun providePaymentCardNavigationMiddleware() = PaymentCardNavigationMiddleware()

    @Provides
    @Singleton
    fun providePaymentCardStore(
        paymentCardMiddleware: PaymentCardMiddleware,
        paymentCardNavigationMiddleware: PaymentCardNavigationMiddleware
    ): PaymentCardStore {
        return PaymentCardStore(
            PaymentCardState(Mode.ADD, false, null, "", "", ""),
            listOf(
                paymentCardMiddleware,
                paymentCardNavigationMiddleware
            ),
            PaymentCardReducer()
        )
    }

}

