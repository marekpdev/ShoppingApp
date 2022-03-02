package com.marekpdev.shoppingapp.models

/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
data class Category (
    val id: Int,
    val parentCategoryId: Int,
    val name: String,
    val image: String
)

const val ROOT_CATEGORY_ID = 0
const val NO_CATEGORY_ID = 0