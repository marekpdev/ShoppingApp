package com.marekpdev.shoppingapp.repository.basket

import com.marekpdev.shoppingapp.models.BasketProduct
import com.marekpdev.shoppingapp.models.Color
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.models.Size
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.concurrent.atomic.AtomicLong
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 05/07/2022.
 */
class BasketRepositoryImpl @Inject constructor(): BasketRepository {

    private val basketProductId = AtomicLong()
    private val basketProducts = MutableStateFlow(emptyList<BasketProduct>())

    override suspend fun addToBasket(product: Product,
                                     selectedSize: Size?,
                                     selectedColor: Color?) {
        val updatedBasketProducts = basketProducts.value.toMutableList().apply {
            add(createBasketProduct(product, selectedSize, selectedColor))
        }
        basketProducts.emit(updatedBasketProducts)
    }

    override suspend fun updateBasketProduct(basketProductId: Long,
                                             selectedSize: Size?,
                                             selectedColor: Color?) {
        val updatedBasketProducts = basketProducts.value.map { basketProduct ->
            when (basketProduct.id == basketProductId) {
                true -> basketProduct.copy(selectedSize = selectedSize, selectedColor = selectedColor)
                else -> basketProduct
            }
        }
        basketProducts.emit(updatedBasketProducts)
    }

    override suspend fun removeFromBasket(basketProduct: BasketProduct) {
        val updatedBasketProducts = basketProducts.value.toMutableList().apply {
            remove(basketProduct)
        }
        basketProducts.emit(updatedBasketProducts)
    }

    private fun createBasketProduct(product: Product,
                                    selectedSize: Size?,
                                    selectedColor: Color?): BasketProduct {
        return BasketProduct(
            id = basketProductId.getAndIncrement(),
            productId = product.id,
            name = product.name,
            description = product.description,
            price = product.price,
            currency = product.currency,
            images = product.images,
            selectedSize = selectedSize,
            selectedColor = selectedColor
        )
    }
}