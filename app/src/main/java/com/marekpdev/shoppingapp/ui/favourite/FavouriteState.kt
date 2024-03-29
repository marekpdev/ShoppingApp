package com.marekpdev.shoppingapp.ui.favourite

import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.mvi.State

/**
 * Created by Marek Pszczolka on 04/06/2022.
 */
data class FavouriteState(
    val products: List<Product>,
    val loading: Boolean
): State


