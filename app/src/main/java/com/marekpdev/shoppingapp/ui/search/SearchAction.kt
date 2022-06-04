package com.marekpdev.shoppingapp.ui.search

import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.mvi.Action

/**
 * Created by Marek Pszczolka on 04/06/2022.
 */
sealed class SearchAction : Action {

    class SearchQueryChanged(val query: String): SearchAction()
    object SearchStarted: SearchAction()
    class SearchSuccess(val products: List<Product>): SearchAction()
    class SearchError(val error: Throwable?): SearchAction()

    class ProductClicked(val productId: Long): SearchAction()

}