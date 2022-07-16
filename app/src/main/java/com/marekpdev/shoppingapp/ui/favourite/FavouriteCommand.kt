package com.marekpdev.shoppingapp.ui.favourite

import com.marekpdev.shoppingapp.mvi.Command
import com.marekpdev.shoppingapp.ui.search.SearchCommand

/**
 * Created by Marek Pszczolka on 12/07/2022.
 */
sealed class FavouriteCommand: Command {

    class GoToProductDetailsScreen(val productId: Long): FavouriteCommand()
}