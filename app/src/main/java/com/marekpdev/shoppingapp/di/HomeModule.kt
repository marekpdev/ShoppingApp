package com.marekpdev.shoppingapp.di

import com.marekpdev.shoppingapp.repository.homebanners.HomeBannersRepository
import com.marekpdev.shoppingapp.repository.homebanners.HomeBannersRepositoryImpl
import com.marekpdev.shoppingapp.repository.products.ProductsRepository
import com.marekpdev.shoppingapp.repository.products.ProductsRepositoryImpl
import com.marekpdev.shoppingapp.ui.home.HomeMiddleware
import com.marekpdev.shoppingapp.ui.home.HomeReducer
import com.marekpdev.shoppingapp.ui.home.HomeState
import com.marekpdev.shoppingapp.ui.home.HomeStore
import dagger.Binds
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
class HomeModule {

    @Provides
    @Singleton
    fun provideHomeMiddleware(
        productsRepository: ProductsRepository,
        homeBannersRepository: HomeBannersRepository
    ) = HomeMiddleware(productsRepository, homeBannersRepository)

    @Provides
    @Singleton
    fun provideHomeStore(
        homeMiddleware: HomeMiddleware,
    ): HomeStore {
        return HomeStore(
            HomeState(emptyList(), false, emptyList(), false),
            listOf(
                homeMiddleware
            ),
            HomeReducer()
        )
    }

}

@Module
@InstallIn(SingletonComponent::class)
interface HomeDependencyBinder {

    @Binds
    @Singleton
    fun bindHomeBannersRepository(repositoryImpl: HomeBannersRepositoryImpl): HomeBannersRepository

}