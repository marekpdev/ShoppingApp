package com.marekpdev.shoppingapp.ui.home

import com.marekpdev.shoppingapp.models.DisplayPlace
import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.repository.homebanners.HomeBannersRepository
import com.marekpdev.shoppingapp.repository.products.ProductsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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
        productsRepository.getAllMenu()
            .onEach { allMenu ->
                val productRecommendations = allMenu.categories
                    .filter { category -> category.displayPlace == DisplayPlace.HOME }
                    .map { category -> category to allMenu.products.filter { product -> category.id in product.parentCategoryIds } }

                requestAction(HomeAction.RefreshProductRecommendations(productRecommendations))
            }
            .launchIn(coroutineScope)

        homeBannersRepository.getHomeBanners()
            .onEach { homeBanners -> requestAction(HomeAction.RefreshHomeBanners(homeBanners)) }
            .launchIn(coroutineScope)
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
        productsRepository.toggleFavourite(action.product)
    }
}