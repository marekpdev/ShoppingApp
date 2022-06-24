package com.marekpdev.shoppingapp.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.marekpdev.shoppingapp.models.Color
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.models.Size

/**
 * Created by Marek Pszczolka on 16/03/2022.
 */
open class JSONConverter<T> {

    private val gson by lazy { Gson() }

    @TypeConverter
    fun toString(value: List<T>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toList(value: String): List<T> {
        val type = object : TypeToken<List<T>>() {}.type
        return gson.fromJson(value, type)
    }

}

class ProductListConverter: JSONConverter<Product>()
class ColorListConverter: JSONConverter<Color>()
class SizeListConverter: JSONConverter<Size>()
class StringListConverter: JSONConverter<String>()