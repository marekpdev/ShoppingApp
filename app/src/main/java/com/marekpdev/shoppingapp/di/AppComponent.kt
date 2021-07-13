package com.marekpdev.shoppingapp.di

import com.marekpdev.shoppingapp.ui.MainActivity
import dagger.Component

/**
 * Created by Marek Pszczolka on 13/07/2021.
 */
@Component(modules = [DatabaseModule::class, NetworkModule::class, ProductsModule::class, StorageModule::class])
interface AppComponent {
    // Classes that can be injected by this Component
    fun inject(activity: MainActivity)
}