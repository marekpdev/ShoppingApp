package com.marekpdev.shoppingapp.di

import com.marekpdev.shoppingapp.repository.addresses.AddressesRepository
import com.marekpdev.shoppingapp.repository.basket.BasketRepository
import com.marekpdev.shoppingapp.repository.orders.OrdersRepository
import com.marekpdev.shoppingapp.repository.paymentmethods.PaymentMethodsRepository
import com.marekpdev.shoppingapp.repository.user.UserRepository
import com.marekpdev.shoppingapp.ui.checkout.*
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
object CheckoutModule {

    @Provides
    @Singleton
    fun provideCheckoutMiddleware(userRepository: UserRepository,
                                  basketRepository: BasketRepository,
                                  addressesRepository: AddressesRepository,
                                  paymentMethodsRepository: PaymentMethodsRepository,
                                  ordersRepository: OrdersRepository) =
        CheckoutMiddleware(userRepository, basketRepository, addressesRepository, paymentMethodsRepository, ordersRepository)

    @Provides
    @Singleton
    fun provideCheckoutNavigationMiddleware() = CheckoutNavigationMiddleware()

    @Provides
    @Singleton
    fun provideCheckoutStore(
        checkoutMiddleware: CheckoutMiddleware,
        checkoutNavigationMiddleware: CheckoutNavigationMiddleware
    ): CheckoutStore {
        return CheckoutStore(
            CheckoutState(emptyList(), null, emptyList(), null, 0.0, false, false),
            listOf(
                checkoutMiddleware,
                checkoutNavigationMiddleware
            ),
            CheckoutReducer()
        )
    }

}

