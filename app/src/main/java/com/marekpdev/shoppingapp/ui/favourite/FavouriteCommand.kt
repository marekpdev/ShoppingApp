package com.marekpdev.shoppingapp.ui.favourite

import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.mvi.Command

/**
 * Created by Marek Pszczolka on 12/07/2022.
 */
sealed class FavouriteCommand: Command {

    class GoToProductDetailsScreen(val productId: Long): FavouriteCommand() // todo change to data class?
    class ShowProductUnfavoured(val product: Product): FavouriteCommand() // todo change to data class?
}