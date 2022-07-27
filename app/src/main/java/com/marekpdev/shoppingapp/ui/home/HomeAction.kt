package com.marekpdev.shoppingapp.ui.home

import com.marekpdev.shoppingapp.models.Category
import com.marekpdev.shoppingapp.models.HomeBanner
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.mvi.Action

/**
 * Created by Marek Pszczolka on 20/07/2022.
 */
sealed class HomeAction: Action {
    object LoadingProductRecommendations: HomeAction()
    data class RefreshProductRecommendations(val productRecommendations: List<Pair<Category, List<Product>>>): HomeAction()

    object LoadingBanners: HomeAction()
    data class RefreshHomeBanners(val homeBanners: List<HomeBanner>): HomeAction()

    class ProductClicked(val productId: Long): HomeAction()
    class ShowMoreClicked(val categoryId: Int): HomeAction()

    class ToggleFavouriteClicked(val product: Product): HomeAction()
}