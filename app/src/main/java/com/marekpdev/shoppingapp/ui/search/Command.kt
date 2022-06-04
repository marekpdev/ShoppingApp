package com.marekpdev.shoppingapp.ui.search

/**
 * Created by Marek Pszczolka on 04/06/2022.
 */
sealed class Command {

    class GoToProductDetailsScreen(val productId: Long): Command()

}