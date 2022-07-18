package com.marekpdev.shoppingapp.navigation

/**
 * Created by Marek Pszczolka on 18/07/2022.
 */
interface OnBackPressedCallback {

    /**
     * Returns true if 'on back' click has been handled
     */
    fun onBackPressed(): Boolean

}