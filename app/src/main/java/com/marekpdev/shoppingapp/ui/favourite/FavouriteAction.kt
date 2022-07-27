package com.marekpdev.shoppingapp.ui.favourite

import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.mvi.Action

/**
 * Created by Marek Pszczolka on 12/07/2022.
 */
sealed class FavouriteAction : Action {

    object FetchInitialData: FavouriteAction()
    object Loading: FavouriteAction()
    class LoadingError(val error: Throwable?): FavouriteAction()
    data class RefreshData(val products: List<Product>): FavouriteAction()

    class ProductClicked(val productId: Long): FavouriteAction()
    class ToggleFavouriteClicked(val product: Product): FavouriteAction()

}