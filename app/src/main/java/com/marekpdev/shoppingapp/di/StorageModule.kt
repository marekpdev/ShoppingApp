package com.marekpdev.shoppingapp.di

import com.marekpdev.shoppingapp.storage.SharedPreferencesStorage
import com.marekpdev.shoppingapp.storage.Storage
import dagger.Binds
import dagger.Module

/**
 * Created by Marek Pszczolka on 13/07/2021.
 */
//@Module
//abstract class StorageModule {
//
//    @Binds
//    abstract fun provideStorage(storage: SharedPreferencesStorage): Storage
//
//    // todo should i merge it with DatabaseModule? should it then be object or abstract class?
//}