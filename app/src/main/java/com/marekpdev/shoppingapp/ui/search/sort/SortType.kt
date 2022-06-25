package com.marekpdev.shoppingapp.ui.search.sort

import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.ui.search.IntermediateSelection

/**
 * Created by Marek Pszczolka on 07/06/2022.
 */
data class SortType(
    val type: IntermediateSelection<Type>
) {

    companion object {
        val INIT = SortType(
            IntermediateSelection(Type.PRICE_LOWEST_FIRST, Type.PRICE_LOWEST_FIRST, Type.PRICE_LOWEST_FIRST)
        )
    }

    enum class Type(val comparator: Comparator<Product>) {

        PRICE_LOWEST_FIRST(
            Comparator { product1, product2 -> product1.price.compareTo(product2.price) }
        ),
        PRICE_HIGHEST_FIRST(
            Comparator { product1, product2 -> product2.price.compareTo(product1.price) }
        )
    }

    fun confirmSelection() = copy(
        type = type.confirmSelection()
    )
}