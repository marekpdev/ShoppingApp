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
                   onSizeClicked: (Size) -> Unit): Chip {

        return Chip(context).apply {

            setOnClickListener { onSizeClicked(size) }

            text = size.name

            val backgroundColor = ContextCompat.getColor(context, R.color.white)
            val strokeColor = ContextCompat.getColor(context, R.color.colorPrimaryDark)

            // todo
            // not sure if we can reuse the states or we need to create
            // new values for each use in ColorStateList
            val states = arrayOf(
                    intArrayOf(android.R.attr.state_enabled),
                    intArrayOf(-android.R.attr.state_enabled),
                    intArrayOf(-android.R.attr.state_checked),
                    intArrayOf(android.R.attr.state_pressed)
            )

            chipBackgroundColor = ColorStateList(
                    states,
                    intArrayOf(
                            backgroundColor,
                            Utils.adjustAlpha(backgroundColor, 0.30f),
                            backgroundColor,
                            backgroundColor
                    )
            )

            chipStrokeColor = ColorStateList(
                    states,
                    intArrayOf(
                            strokeColor,
                            Utils.adjustAlpha(strokeColor, 0.30f),
                            strokeColor,
                            strokeColor
                    )
            )

            chipStrokeWidth = context.resources.getDimension(R.dimen.chip_stroke_width)
            setTextAppearance(R.style.ChipTextAppearance)

            shapeAppearanceModel = ShapeAppearanceModel()
                    .toBuilder()
                    .setAllCorners(
                            CornerFamily.ROUNDED,
                            context.resources.getDimension(R.dimen.chip_corner_radius)
                    )
                    .build()

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
        }

    }







}