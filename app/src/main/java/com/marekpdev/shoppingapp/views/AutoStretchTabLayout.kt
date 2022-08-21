package com.marekpdev.shoppingapp.views

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.core.view.children
import androidx.core.view.doOnLayout
import androidx.core.view.get
import com.google.android.material.tabs.TabLayout

/**
 * Created by Marek Pszczolka on 21/08/2022.
 */
class AutoStretchTabLayout : TabLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        doOnLayout {
            val tabLayout = this[0] as ViewGroup

            val tabsTotalWidth = tabLayout.children.sumOf { it.width }

            val freeWidth = tabLayout.width - tabsTotalWidth
            val freeWidthPerTab = freeWidth / tabLayout.childCount

            if(freeWidthPerTab > 0) {
                tabLayout.children.forEach {
                    val layoutParams = it.layoutParams
                    layoutParams.width = it.width + freeWidthPerTab
                    it.layoutParams = layoutParams
                }
            }
        }
    }
}