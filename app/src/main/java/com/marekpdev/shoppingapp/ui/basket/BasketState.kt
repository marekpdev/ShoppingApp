package com.marekpdev.shoppingapp.ui.basket

import com.marekpdev.shoppingapp.models.BasketProduct
import com.marekpdev.shoppingapp.mvi.State

/**
 * Created by Marek Pszczolka on 25/07/2022.
 */
data class BasketState(
    val basketProducts: List<BasketProduct>,
    val loading: Boolean,
    val totalCost: Double
) : State