package com.marekpdev.shoppingapp.views

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.ViewGroup
import android.view.ViewTreeObserver
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

    init {
        val globalLayoutListener = object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                Log.d("FEO600", "globalLayoutListener" + System.currentTimeMillis())
                stretchTabs()
            }
        }
        viewTreeObserver.addOnGlobalLayoutListener(globalLayoutListener)

//        doOnNextLayout {
//            Log.d("FEO600", "doOnNextLayout" + System.currentTimeMillis())
//            stretchTabs()
//        }
    }

    fun stretchTabs() {
        Log.d("FEO600", "stretchTabs" + System.currentTimeMillis())
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