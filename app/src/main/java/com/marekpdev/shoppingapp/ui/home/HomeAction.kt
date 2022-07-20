package com.marekpdev.shoppingapp.ui.home

import com.marekpdev.shoppingapp.models.Category
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.mvi.Action
import com.marekpdev.shoppingapp.ui.search.SearchAction

/**
 * Created by Marek Pszczolka on 20/07/2022.
 */
sealed class HomeAction: Action {
    object LoadingBanners: HomeAction()
    object LoadingProductRecommendations: HomeAction()
    data class RefreshProductRecommendations(val productRecommendations: List<Pair<Category, List<Product>>>): HomeAction()

    class ProductClicked(val productId: Long): HomeAction()
    class ShowMoreClicked(val categoryId: Int): HomeAction()

    class ToggleFavouriteClicked(val product: Product): HomeAction()
}