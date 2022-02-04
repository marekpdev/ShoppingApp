package com.marekpdev.shoppingapp.domain

import com.marekpdev.shoppingapp.models.Product

/**
 * Created by Marek Pszczolka on 11/07/2021.
 */
data class ProductResponse(val product: Product)

data class ProductsResponse(val products: List<Product>)