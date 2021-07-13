package com.marekpdev.shoppingapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.marekpdev.MyApplication
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.ActivityMainBinding
import com.marekpdev.shoppingapp.di.AppComponentProvider
import com.marekpdev.shoppingapp.location.LocationTracker
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var locationTracker: LocationTracker

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        // Ask Dagger to inject our dependencies
        (application as AppComponentProvider).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.navHostContainer
        ) as NavHostFragment
        navController = navHostFragment.navController

        binding.apply {
            // Setup the bottom navigation view with navController
            bottomNav.setupWithNavController(navController)
        }

        // Setup the ActionBar with navController and 5 top level destinations
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.home, R.id.search, R.id.checkout, R.id.favourite, R.id.account)
        )

        // todo - commented out because we are not using standard toolbar?
//        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }
}