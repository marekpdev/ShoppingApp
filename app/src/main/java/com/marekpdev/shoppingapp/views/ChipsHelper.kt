package com.marekpdev.shoppingapp.views

import android.content.Context
import android.content.res.ColorStateList
import androidx.core.content.ContextCompat
import com.google.android.material.chip.Chip
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.ShapeAppearanceModel
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.Utils
import com.marekpdev.shoppingapp.models.Size

/**
 * Created by Marek Pszczolka on 25/04/2021.
 */
object ChipsHelper {

    fun createChip(context: Context,
                   size: Size,
                   onSizeClicked: (Size) -> Unit): Chip{

        //            val chip = LayoutInflater.from(context).inflate(R.layout.product_size_chip, null) as Chip




        val chip = Chip(context)
        chip.setOnClickListener { onSizeClicked(size) }

        val radius = context.resources.getDimension(R.dimen.chip_corner_radius)
        val strokeWidth = context.resources.getDimension(R.dimen.chip_stroke_width)

        chip.text = size.name

        val backgroundColor = ContextCompat.getColor(context, R.color.white)
        val strokeColor = ContextCompat.getColor(context, R.color.colorPrimaryDark)

        val backgroundColorStates = arrayOf(
            intArrayOf(android.R.attr.state_enabled),
            intArrayOf(-android.R.attr.state_enabled),
            intArrayOf(-android.R.attr.state_checked),
            intArrayOf(android.R.attr.state_pressed)
        )
//
        val backgroundColorColors = intArrayOf(
            backgroundColor,
            Utils.adjustAlpha(backgroundColor, 0.30f),
            backgroundColor,
            backgroundColor
        )

        val strokeColorStates = arrayOf(
            intArrayOf(android.R.attr.state_enabled),
            intArrayOf(-android.R.attr.state_enabled),
            intArrayOf(-android.R.attr.state_checked),
            intArrayOf(android.R.attr.state_pressed)
        )

        val strokeColorColors = intArrayOf(
            strokeColor,
            Utils.adjustAlpha(strokeColor, 0.30f),
            strokeColor,
            strokeColor
        )

        chip.chipBackgroundColor = ColorStateList(backgroundColorStates, backgroundColorColors)
        chip.chipStrokeColor = ColorStateList(strokeColorStates, strokeColorColors)
        chip.chipStrokeWidth = strokeWidth
        chip.setTextAppearance(R.style.ChipTextAppearance)

        val shapeAppearanceModel = ShapeAppearanceModel()
            .toBuilder()
            .setAllCorners(CornerFamily.ROUNDED, radius)
            .build()
        chip.shapeAppearanceModel = shapeAppearanceModel

        // some guy said
        // yeah, can add chips to group with  chipGroup.addView(chip);
        // please note that you should add id to each chip in order
        // to some functionality of group work perfectly
        // (e.g singleSelection="true" )
        // chip.setId(ViewCompat.generateViewId());
        // probably not needed but might be worth checking later

        // we had main parent style from
        //  <style name="ChipBackground" parent="Widget.MaterialComponents.Chip.Choice">
        // also not sure about
        //  <item name="android:selectableItemBackground">@drawable/circle_background</item>

        return chip

    }





}