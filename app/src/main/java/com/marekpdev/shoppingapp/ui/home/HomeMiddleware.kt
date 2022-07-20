package com.marekpdev.shoppingapp.ui.home

import android.util.Log
import com.marekpdev.shoppingapp.models.Category
import com.marekpdev.shoppingapp.models.DisplayPlace
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.repository.homebanners.HomeBannersRepository
import com.marekpdev.shoppingapp.repository.products.ProductsRepository
import com.marekpdev.shoppingapp.ui.search.SearchAction
import com.marekpdev.shoppingapp.ui.search.SearchCommand
import com.marekpdev.shoppingapp.ui.search.SearchState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 20/07/2022.
 */
class HomeMiddleware @Inject constructor(private val productsRepository: ProductsRepository,
                                         private val homeBannersRepository: HomeBannersRepository)
    : Middleware<HomeState, HomeAction, HomeCommand> {

    override suspend fun bind(
        coroutineScope: CoroutineScope,
        state: StateFlow<HomeState>,
        requestAction: suspend (HomeAction) -> Unit
    ) {
        coroutineScope.launch {
            productsRepository.getAllMenu()
                .collectLatest { allMenu ->
                    Log.d("FEO999", "Mapping HomeMiddleware 1")

                    val productRecommendations = allMenu.categories
                        .filter { category -> category.displayPlace == DisplayPlace.HOME }
                        .map { category -> category to allMenu.products.filter { product -> category.id in product.parentCategoryIds } }

                    requestAction(HomeAction.RefreshProductRecommendations(productRecommendations))
                }
        }

        coroutineScope.launch {
            homeBannersRepository.getHomeBanners()
                .collectLatest { homeBanners ->
                    Log.d("FEO999", "Mapping homeBanners 1")
                    requestAction(HomeAction.RefreshHomeBanners(homeBanners))
                }
        }
    }

    override suspend fun process(
        action: HomeAction,
        currentState: HomeState,
        requestAction: suspend (HomeAction) -> Unit,
        requestCommand: suspend (HomeCommand) -> Unit
    ) {
        when(action){
            is HomeAction.ToggleFavouriteClicked -> onToggleFavourite(action, currentState, requestAction, requestCommand)
        }
    }

    private suspend fun onToggleFavourite(
        action: HomeAction.ToggleFavouriteClicked,
        currentState: HomeState,
        requestAction: suspend (HomeAction) -> Unit,
        requestCommand: suspend (HomeCommand) -> Unit
    ) {
        Log.d("FEO999", "Toggle favourite: ${action.product.name}")
        productsRepository.toggleFavourite(action.product)
    }
}