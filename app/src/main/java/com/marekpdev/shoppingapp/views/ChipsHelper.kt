package com.marekpdev.shoppingapp.views

import android.content.Context
import androidx.core.content.ContextCompat
import com.google.android.material.chip.Chip
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.ShapeAppearanceModel
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.utils.Utils
import com.marekpdev.shoppingapp.models.Color
import com.marekpdev.shoppingapp.models.Size

/**
 * Created by Marek Pszczolka on 25/04/2021.
 * There was an issue with creating Chip layouts dynamically
 * (based on the amount of data coming from API) - creating
 * a new layout with
 * LayoutInflater.from(context).inflate(R.layout.product_color_chip, null) as Chip
 * for some reason didn't properly take the style attached into account.
 * So it was far better to create Chip layouts dynamically as below
 */
object ChipsHelper {

    fun createChip(context: Context,
                   size: Size): Chip {

        return Chip(context).apply {

            isCheckable = true
            isCheckedIconVisible = false

            text = size.name

            val backgroundColor = ContextCompat.getColor(context, R.color.white)
            val strokeColor = ContextCompat.getColor(context, R.color.colorPrimaryDark)

            chipBackgroundColor = ColorStateListHelper.colorStateListOf(
                    ViewState.CHECKED to ContextCompat.getColor(context, R.color.colorAccent),
                    ViewState.DISABLED to Utils.adjustAlpha(backgroundColor, 0.30f),
                    ViewState.DEFAULT to backgroundColor
            )

            chipStrokeColor = ColorStateListHelper.colorStateListOf(
                    ViewState.CHECKED to ContextCompat.getColor(context, R.color.colorAccent),
                    ViewState.DISABLED to Utils.adjustAlpha(strokeColor, 0.30f),
                    ViewState.DEFAULT to strokeColor
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

    fun createChip(context: Context,
                   color: Color): Chip {
        return Chip(context).apply {

            isCheckable = true
            isCheckedIconVisible = false

            val colorParsed = android.graphics.Color.parseColor(color.rgbHex)
            val strokeColor = ContextCompat.getColor(context, R.color.transparent)

            chipBackgroundColor = ColorStateListHelper.colorStateListOf(
                    ViewState.CHECKED to colorParsed,
                    ViewState.DISABLED to Utils.adjustAlpha(colorParsed, 0.30f),
                    ViewState.DEFAULT to colorParsed
            )

            chipStrokeColor = ColorStateListHelper.colorStateListOf(
                    ViewState.CHECKED to ContextCompat.getColor(context, R.color.black),
                    ViewState.DISABLED to ContextCompat.getColor(context, R.color.transparent),
                    ViewState.DEFAULT to ContextCompat.getColor(context, R.color.transparent),
            )

            chipStrokeWidth = context.resources.getDimension(R.dimen.chip_stroke_width)

            shapeAppearanceModel = ShapeAppearanceModel()
                    .toBuilder()
                    .setAllCorners(
                            CornerFamily.ROUNDED,
                            context.resources.getDimension(R.dimen.chip_corner_radius_big)
                    )
                    .build()

            width = context.resources.getDimension(R.dimen.chip_color_width).toInt()
            height = context.resources.getDimension(R.dimen.chip_color_height).toInt()

        }
    }

}