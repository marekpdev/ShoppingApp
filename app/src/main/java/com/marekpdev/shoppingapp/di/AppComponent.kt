package com.marekpdev.shoppingapp.di

import android.content.Context
import com.marekpdev.shoppingapp.ui.MainActivity
import dagger.BindsInstance
import dagger.Component

/**
 * Created by Marek Pszczolka on 13/07/2021.
 */
@AppScope
@Component(modules = [
    DatabaseModule::class,
    NetworkModule::class,
    ProductsModule::class,
    StorageModule::class
])
interface AppComponent {

    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): AppComponent
    }

    // Classes that can be injected by this Component
    fun inject(activity: MainActivity)

}