package com.marekpdev.shoppingapp.repository.basket

import com.marekpdev.shoppingapp.models.BasketProduct
import com.marekpdev.shoppingapp.models.Color
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.models.Size
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.concurrent.atomic.AtomicLong
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 05/07/2022.
 */
class BasketRepositoryImpl @Inject constructor(): BasketRepository {

    private val basketProductId = AtomicLong()
    private val basketProducts = MutableStateFlow(emptyList<BasketProduct>())

    override suspend fun observeBasketProducts(): StateFlow<List<BasketProduct>> {
        return basketProducts
    }

    override suspend fun addToBasket(product: Product,
                                     selectedSize: Size?,
                                     selectedColor: Color?) {
        val updatedBasketProducts = basketProducts.value.toMutableList().apply {
            add(product.toBasketProduct(selectedSize, selectedColor))
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

    override suspend fun updateSize(basketProduct: BasketProduct, size: Size) {
        val updatedBasketProducts = basketProducts.value.map {
            when (it.id == basketProduct.id) {
                true -> it.copy(selectedSize = size)
                else -> it
            }
        }
        basketProducts.emit(updatedBasketProducts)
    }

    override suspend fun updateColor(basketProduct: BasketProduct, color: Color) {
        val updatedBasketProducts = basketProducts.value.map {
            when (it.id == basketProduct.id) {
                true -> it.copy(selectedColor = color)
                else -> it
            }
        }
        basketProducts.emit(updatedBasketProducts)
    }

    private fun Product.toBasketProduct(selectedSize: Size?, selectedColor: Color?): BasketProduct {
        return BasketProduct(
            id = basketProductId.getAndIncrement(),
            productId = id,
            name = name,
            description = description,
            price = price,
            currency = currency,
            availableColors = availableColors,
            availableSizes = availableSizes,
            images = images,
            selectedSize = selectedSize,
            selectedColor = selectedColor
        )
    }

}