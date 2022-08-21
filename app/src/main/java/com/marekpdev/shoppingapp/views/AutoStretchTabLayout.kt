package com.marekpdev.shoppingapp.views

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.core.view.*
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

    fun updateTabs(tabs: Collection<Tab>){
        doOnNextLayout { stretchTabs() }
        removeAllTabs()
        tabs.forEach { addTab(it) }
    }

    private fun stretchTabs() {
        val tabLayout = this[0] as ViewGroup

        if(tabLayout.childCount == 0) return

        val tabsTotalWidth = tabLayout.children.sumOf { it.width }

        val freeWidth = tabLayout.width - tabsTotalWidth

        if(freeWidth > 0) {
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