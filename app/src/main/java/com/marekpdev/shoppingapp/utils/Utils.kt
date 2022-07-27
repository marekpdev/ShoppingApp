package com.marekpdev.shoppingapp.utils

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

    /**
     *
     * @param color
     * @param factor if < 1 color darker - if > 1 color lighter
     * @return
     */
    fun alterColor(color: Int, factor: Float): Int {
        val hsv = FloatArray(3)
        Color.RGBToHSV(Color.red(color), Color.green(color), Color.blue(color), hsv)
        hsv[2] = hsv[2] * factor
        return Color.HSVToColor(hsv)
    }

}