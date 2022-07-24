package com.marekpdev.shoppingapp.models

/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
data class Category (
    val id: Int,
    val parentCategoryId: Int?,
    val name: String,
    val displayPlace: DisplayPlace
) {
    fun isRootCategory() = id == ROOT_CATEGORY_ID
}

enum class DisplayPlace (val jsonString: String) {
    MENU("menu"),
    HOME("home"),
    PRODUCT_RECOMMENDATIONS("product_recommendations")
}

const val ROOT_CATEGORY_ID = 0