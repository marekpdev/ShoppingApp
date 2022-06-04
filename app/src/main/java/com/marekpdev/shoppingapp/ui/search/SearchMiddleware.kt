package com.marekpdev.shoppingapp.ui.search

import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.repository.Data

/**
 * Created by Marek Pszczolka on 04/06/2022.
 */
class SearchMiddleware: Middleware<SearchViewState, SearchAction> {

    private val allProducts = Data.getMenu().second!!

    override fun dispatch(action: SearchAction) {

    }
}