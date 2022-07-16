package com.marekpdev.shoppingapp.ui.product

import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.mvi.Store

/**
 * Created by Marek Pszczolka on 05/07/2022.
 */
class ProductStore (
    initialState: ProductState,
    middlewares: List<Middleware<ProductState, ProductAction, ProductCommand>>,
    reducer: ProductReducer
) : Store<ProductState, ProductAction, ProductCommand>(initialState, middlewares, reducer)