package com.marekpdev.shoppingapp.ui.product

import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.repository.products.ProductsRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 05/07/2022.
 */
class ProductMiddleware @Inject constructor(private val productsRepository: ProductsRepository)
    : Middleware<ProductState, ProductAction, ProductCommand> {

    override fun bind(
        actions: Observable<ProductAction>,
        state: Observable<ProductState>,
        requestAction: (ProductAction) -> Unit,
        requestCommand: (ProductCommand) -> Unit
    ): Observable<ProductAction> {
        TODO("Not yet implemented")
    }
}