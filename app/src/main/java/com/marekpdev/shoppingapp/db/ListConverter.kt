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
open class ListConverter<T> {

    @TypeConverter
    fun toString(value: List<T>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toList(value: String): List<T> {
        val type = object : TypeToken<List<T>>() {}.type
        return Gson().fromJson(value, type)
    }

}

class ProductConverter: ListConverter<Product>()
class ColorConverter: ListConverter<Color>()
class SizeConverter: ListConverter<Size>()
class StringListConverter: ListConverter<String>()