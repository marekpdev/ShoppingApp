package com.marekpdev.shoppingapp.ui.product

import com.marekpdev.shoppingapp.models.Color
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.models.Size
import com.marekpdev.shoppingapp.mvi.State

/**
 * Created by Marek Pszczolka on 05/07/2022.
 */
data class ProductState (
    val product: Product?,
    val selectedSize: Size?,
    val selectedColor: Color?,
    val loading: Boolean
): State