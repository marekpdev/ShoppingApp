package com.marekpdev.shoppingapp.ui.search

/**
 * Created by Marek Pszczolka on 25/06/2022.
 * Used for storing information which
 * helps us with sorting and filtering data
 */
data class IntermediateSelection<T>(
    /**
     * All available data that we are able to choose from
     */
    val available: T,
    /**
     * The data that is currently applied to some other data source
     */
    val applied: T,
    /**
     * The data that we currently see during our selection process
     * (only reflected on the screen but not actually applied to
     * other data source yet)
     */
    val selected: T
) {

    /**
     * When we confirm our intermediate selection we
     * 'apply' the data that we 'selected'
     */
    fun confirmSelection() = copy(
        available = available,
        applied = selected,
        selected = selected
    )
}