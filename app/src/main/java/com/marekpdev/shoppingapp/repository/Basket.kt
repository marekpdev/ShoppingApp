package com.marekpdev.shoppingapp.repository

import com.marekpdev.shoppingapp.models.Product

/**
 * Created by Marek Pszczolka on 03/03/2022.
 */
object Basket {

    val basketItems = mutableListOf<Product>().apply {
        addAll(Data.getProducts(4, 1))
    }

}