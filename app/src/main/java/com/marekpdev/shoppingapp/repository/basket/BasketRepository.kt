package com.marekpdev.shoppingapp.repository.basket

import com.marekpdev.shoppingapp.models.BasketProduct
import com.marekpdev.shoppingapp.models.Color
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.models.Size
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.flow.StateFlow

/**
 * Created by Marek Pszczolka on 12/07/2021.
 */
interface BasketRepository {

    suspend fun addToBasket(product: Product,
                            selectedSize: Size?,
                            selectedColor: Color?)

    suspend fun updateBasketProduct(basketProductId: Long,
                                    selectedSize: Size?,
                                    selectedColor: Color?)

    suspend fun removeFromBasket(basketProduct: BasketProduct)

    suspend fun observeBasketProducts(): StateFlow<List<BasketProduct>>

    suspend fun updateSize(basketProduct: BasketProduct, size: Size)

    suspend fun updateColor(basketProduct: BasketProduct, color: Color)

}