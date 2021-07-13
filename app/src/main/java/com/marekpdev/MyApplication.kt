package com.marekpdev

import android.app.Application
import com.marekpdev.shoppingapp.di.AppComponent
import com.marekpdev.shoppingapp.di.DaggerAppComponent

/**
 * Created by Marek Pszczolka on 13/07/2021.
 */
open class MyApplication : Application() {

    // Instance of the AppComponent that will be used by all the Activities in the project
    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        // Creates an instance of AppComponent using its Factory constructor
        // We pass the applicationContext that will be used as Context in the graph
        return DaggerAppComponent.factory().create(applicationContext)
    }
}
