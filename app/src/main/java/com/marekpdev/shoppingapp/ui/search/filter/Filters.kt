package com.marekpdev.shoppingapp.ui.search.filter

import com.marekpdev.shoppingapp.models.Color
import com.marekpdev.shoppingapp.models.Size
import com.marekpdev.shoppingapp.ui.search.IntermediateSelection

/**
 * Created by Marek Pszczolka on 24/06/2022.
 */
data class Filters (
    val colors: IntermediateSelection<List<Color>>,
    val sizes: IntermediateSelection<List<Size>>,
    val priceRange: IntermediateSelection<IntRange>,
) {
    companion object {
        val INIT = Filters(
            IntermediateSelection(emptyList(), emptyList(), emptyList()),
            IntermediateSelection(emptyList(), emptyList(), emptyList()),
            IntermediateSelection(IntRange.EMPTY, IntRange.EMPTY, IntRange.EMPTY)
        )
    }
}


