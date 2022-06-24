package com.marekpdev.shoppingapp.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 07/05/2022.
 */
abstract class BaseFragment: Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    internal inline fun <reified T : ViewModel> getViewModel(): T =
        ViewModelProvider(this, viewModelFactory).get(T::class.java)

    //fun getBaseActivity(): BaseActivity = requireActivity() as BaseActivity

    //protected val navController: NavController by lazy { getBaseActivity().navController }

}