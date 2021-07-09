package com.marekpdev.shoppingapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.marekpdev.shoppingapp.R

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_container
        ) as NavHostFragment
        navController = navHostFragment.navController

        // Setup the bottom navigation view with navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNavigationView.setupWithNavController(navController)

        // Setup the ActionBar with navController and 5 top level destinations
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.home, R.id.search, R.id.checkout, R.id.favourite, R.id.account)
        )
        // todo - commented out because we are not using standard toolbar?
//        setupActionBarWithNavController(navController, appBarConfiguration)

        // fixme
        // there is still some issue with new navigation library (tested both this app
        // and their 'NavigationAdvancedSample'
        // Tab 1 -> Tab 1 Details (next screen in navigation) -> Tab 2 -> Press back button on the bottom ->
        // Goes back to Tab 1 instead of going to Tab 1 Details
        // (everything was working before migration i.e. when using extension functions for multi backstack)
    }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }
}