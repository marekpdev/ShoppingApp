package com.marekpdev.shoppingapp.ui.product

import com.marekpdev.shoppingapp.models.Color
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.models.Size
import com.marekpdev.shoppingapp.mvi.Action

/**
 * Created by Marek Pszczolka on 05/07/2022.
 */
sealed class ProductAction: Action {

    data class FetchProduct(val productId: Long): ProductAction()
    object Loading: ProductAction()
    data class ProductFetched(val product: Product): ProductAction()
    data class ProductError(val error: Throwable?): ProductAction()

    data class ColorSelected(val color: Color): ProductAction()
    data class SizeSelected(val size: Size): ProductAction()

    object AddProductClicked: ProductAction()

}