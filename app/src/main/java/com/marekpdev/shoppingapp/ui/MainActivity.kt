package com.marekpdev.shoppingapp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.ActivityMainBinding
import com.marekpdev.shoppingapp.navigation.OnBackPressedCallback
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
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

    override fun onBackPressed() {
        val current =  supportFragmentManager.primaryNavigationFragment?.childFragmentManager?.fragments?.first()!!
        Log.d("TEST", "onBackPressed ${current.javaClass.canonicalName}")
        var onBackHandled = false

        if(current is OnBackPressedCallback){
            onBackHandled = current.onBackPressed()
        }

        if(!onBackHandled) {
            super.onBackPressed()
        }
    }
}