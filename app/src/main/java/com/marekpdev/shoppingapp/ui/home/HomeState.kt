package com.marekpdev.shoppingapp.ui.home

import com.marekpdev.shoppingapp.models.Category
import com.marekpdev.shoppingapp.models.HomeBanner
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.mvi.State

/**
 * Created by Marek Pszczolka on 20/07/2022.
 */
data class HomeState(
    val productRecommendations: List<Pair<Category, List<Product>>>,
    val loadingProductRecommendations: Boolean,
    val homeBanners: List<HomeBanner>,
    val loadingBanners: Boolean
): State
