package com.marekpdev.shoppingapp.models

/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
data class Category (
    val id: Int,
    val parentCategoryId: Int = ROOT_CATEGORY_ID,
    val name: String,
    val image: String
)

const val ROOT_CATEGORY_ID = 0