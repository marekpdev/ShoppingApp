package com.marekpdev.shoppingapp

import android.graphics.Color
import androidx.annotation.ColorInt

/**
 * Created by Marek Pszczolka on 25/04/2021.
 */
object Utils {

    @ColorInt
    fun adjustAlpha(@ColorInt color: Int, factor: Float): Int {
        val alpha = Math.round(Color.alpha(color) * factor)
        val red = Color.red(color)
        val green = Color.green(color)
        val blue = Color.blue(color)
        return Color.argb(alpha, red, green, blue)
    }

}