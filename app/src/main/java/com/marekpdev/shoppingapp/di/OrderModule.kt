package com.marekpdev.shoppingapp.di

import com.marekpdev.shoppingapp.repository.orders.OrdersRepository
import com.marekpdev.shoppingapp.ui.order.OrderMiddleware
import com.marekpdev.shoppingapp.ui.order.OrderReducer
import com.marekpdev.shoppingapp.ui.order.OrderState
import com.marekpdev.shoppingapp.ui.order.OrderStore
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
object OrderModule {

    @Provides
    @Singleton
    fun provideOrderMiddleware(ordersRepository: OrdersRepository) = OrderMiddleware(ordersRepository)

    @Provides
    @Singleton
    fun provideOrderStore(
        orderMiddleware: OrderMiddleware,
    ): OrderStore {
        return OrderStore(
            OrderState(null, false),
            listOf(
                orderMiddleware
            ),
            OrderReducer()
        )
    }

}
