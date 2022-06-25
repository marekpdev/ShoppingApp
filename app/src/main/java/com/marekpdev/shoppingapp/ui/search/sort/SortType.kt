package com.marekpdev.shoppingapp.ui.search.sort

import com.marekpdev.shoppingapp.models.Product

/**
 * Created by Marek Pszczolka on 07/06/2022.
 */
enum class SortType(val comparator: Comparator<Product>) {

    PRICE_LOWEST_FIRST(
        Comparator { product1, product2 -> product1.price.compareTo(product2.price) }
    ),
    PRICE_HIGHEST_FIRST(
        Comparator { product1, product2 -> product2.price.compareTo(product1.price) }
    )
}