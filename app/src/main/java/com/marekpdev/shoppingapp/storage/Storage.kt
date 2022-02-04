package com.marekpdev.shoppingapp.storage

/**
 * Created by Marek Pszczolka on 13/07/2021.
 */
interface Storage {
    fun setString(key: String, value: String)
    fun getString(key: String): String
}