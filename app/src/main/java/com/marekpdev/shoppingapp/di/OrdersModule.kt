package com.marekpdev.shoppingapp.di

import com.marekpdev.shoppingapp.repository.orders.OrdersRepository
import com.marekpdev.shoppingapp.repository.orders.OrdersRepositoryImpl
import com.marekpdev.shoppingapp.repository.user.UserRepository
import com.marekpdev.shoppingapp.ui.orders.*
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
object OrdersModule {

    @Provides
    @Singleton
    fun provideOrdersMiddleware(userRepository: UserRepository, ordersRepository: OrdersRepository) = OrdersMiddleware(userRepository, ordersRepository)

    @Provides
    @Singleton
    fun provideOrdersNavigationMiddleware() = OrdersNavigationMiddleware()

    @Provides
    @Singleton
    fun provideOrdersStore(
        ordersMiddleware: OrdersMiddleware,
        ordersNavigationMiddleware: OrdersNavigationMiddleware
    ): OrdersStore {
        return OrdersStore(
            OrdersState(emptyList(), false),
            listOf(
                ordersMiddleware,
                ordersNavigationMiddleware
            ),
            OrdersReducer()
        )
    }

}

@Module
@InstallIn(SingletonComponent::class)
interface OrdersDependencyBinder {

    @Binds
    @Singleton
    fun bindOrdersRepository(repositoryImpl: OrdersRepositoryImpl): OrdersRepository

}

