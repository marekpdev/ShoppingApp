package com.marekpdev.shoppingapp.di

import com.marekpdev.shoppingapp.repository.products.ProductRepositoryImpl
import com.marekpdev.shoppingapp.repository.products.ProductsRepository
import com.marekpdev.shoppingapp.repository.user.UserRepository
import com.marekpdev.shoppingapp.repository.user.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * Created by Marek Pszczolka on 12/07/2021.
 */
//@Module
//abstract class UserModule {
//
//    @Binds
//    //@Singleton // TODO ???? is Singleton/AppScope needed
//    @AppScope
//    abstract fun bindUserRepository(repositoryImpl: UserRepositoryImpl): UserRepository
//}