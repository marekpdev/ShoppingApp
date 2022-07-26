package com.marekpdev.shoppingapp.ui.basket

import com.marekpdev.shoppingapp.models.BasketProduct
import com.marekpdev.shoppingapp.models.Color
import com.marekpdev.shoppingapp.models.Size
import com.marekpdev.shoppingapp.mvi.Action

/**
 * Created by Marek Pszczolka on 25/07/2022.
 */
sealed class BasketAction: Action {

    object Loading: BasketAction()
    data class RefreshData(val basketProducts: List<BasketProduct>, val totalCost: Double): BasketAction()

    class BasketProductClicked(val basketProduct: BasketProduct): BasketAction()
    class RemoveBasketProduct(val basketProduct: BasketProduct): BasketAction()

    class UpdateSize(val basketProduct: BasketProduct, val size: Size): BasketAction()
    class UpdateColor(val basketProduct: BasketProduct, val color: Color): BasketAction()

    object ContinueCheckout: BasketAction()
}