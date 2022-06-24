package com.marekpdev.shoppingapp.ui.search

import com.marekpdev.shoppingapp.mvi.Command

/**
 * Created by Marek Pszczolka on 05/06/2022.
 */
sealed class SearchCommand: Command {

    class GoToProductDetailsScreen(val productId: Long): SearchCommand()
    object ShowSortBottomSheet : SearchCommand()

}