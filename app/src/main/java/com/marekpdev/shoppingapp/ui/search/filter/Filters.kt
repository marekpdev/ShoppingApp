package com.marekpdev.shoppingapp.ui.search.filter

import com.marekpdev.shoppingapp.models.Color
import com.marekpdev.shoppingapp.models.Size

/**
 * Created by Marek Pszczolka on 24/06/2022.
 */
data class Filters (
    val availableColors: List<Color>,
    val selectedColors: List<Color>,
    val availableSizes: List<Size>,
    val selectedSizes: List<Size>,
    val availablePriceRange: IntRange,
    val selectedPriceRange: IntRange
) {
    companion object {
        val INIT = Filters(
            listOf(),
            listOf(),
            listOf(),
            listOf(),
            IntRange.EMPTY,
            IntRange.EMPTY
        )
    }
}
