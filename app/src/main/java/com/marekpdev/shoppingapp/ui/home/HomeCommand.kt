package com.marekpdev.shoppingapp.ui.home

import com.marekpdev.shoppingapp.mvi.Command

/**
 * Created by Marek Pszczolka on 20/07/2022.
 */
sealed class HomeCommand: Command{

    class GoToProductDetailsScreen(val productId: Long): HomeCommand()
}