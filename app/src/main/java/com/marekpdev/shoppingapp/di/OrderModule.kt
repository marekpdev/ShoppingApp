package com.marekpdev.shoppingapp.di

import com.marekpdev.shoppingapp.repository.basket.BasketRepository
import com.marekpdev.shoppingapp.repository.orders.OrdersRepository
import com.marekpdev.shoppingapp.repository.orders.OrdersRepositoryImpl
import com.marekpdev.shoppingapp.repository.products.ProductsRepositoryImpl
import com.marekpdev.shoppingapp.repository.products.ProductsRepository
import com.marekpdev.shoppingapp.repository.user.UserRepository
import com.marekpdev.shoppingapp.ui.order.OrderMiddleware
import com.marekpdev.shoppingapp.ui.order.OrderReducer
import com.marekpdev.shoppingapp.ui.order.OrderState
import com.marekpdev.shoppingapp.ui.order.OrderStore
import com.marekpdev.shoppingapp.ui.orders.*
import com.marekpdev.shoppingapp.ui.product.ProductMiddleware
import com.marekpdev.shoppingapp.ui.product.ProductReducer
import com.marekpdev.shoppingapp.ui.product.ProductState
import com.marekpdev.shoppingapp.ui.product.ProductStore
import com.marekpdev.shoppingapp.ui.search.*
import com.marekpdev.shoppingapp.ui.search.filter.Filters
import com.marekpdev.shoppingapp.ui.search.sort.SortType
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
