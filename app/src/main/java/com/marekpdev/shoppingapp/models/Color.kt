package com.marekpdev.shoppingapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
@Parcelize
data class Color (
    val id: Int,
    val name: String,
    val rgbHex: String
) : Parcelable