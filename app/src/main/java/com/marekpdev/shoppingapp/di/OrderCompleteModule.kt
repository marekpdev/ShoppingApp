package com.marekpdev.shoppingapp.di

import com.marekpdev.shoppingapp.repository.orders.OrdersRepository
import com.marekpdev.shoppingapp.repository.user.UserRepository
import com.marekpdev.shoppingapp.ui.ordercomplete.*
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
object OrderCompleteModule {

    @Provides
    @Singleton
    fun provideOrderCompleteMiddleware(userRepository: UserRepository, ordersRepository: OrdersRepository) = OrderCompleteMiddleware(userRepository, ordersRepository)

    @Provides
    @Singleton
    fun provideOrderCompleteNavigationMiddleware() = OrderCompleteNavigationMiddleware()

    @Provides
    @Singleton
    fun provideOrderCompleteStore(
        orderCompleteMiddleware: OrderCompleteMiddleware,
        orderCompleteNavigationMiddleware: OrderCompleteNavigationMiddleware
    ): OrderCompleteStore {
        return OrderCompleteStore(
            OrderCompleteState(null, false),
            listOf(
                orderCompleteMiddleware,
                orderCompleteNavigationMiddleware
            ),
            OrderCompleteReducer()
        )
    }

}
