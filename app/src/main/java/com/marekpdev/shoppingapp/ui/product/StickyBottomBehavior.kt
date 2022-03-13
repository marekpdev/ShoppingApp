package com.marekpdev.shoppingapp.ui.product

import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import androidx.coordinatorlayout.widget.CoordinatorLayout
import kotlin.math.max
import kotlin.math.min

/**
 * Created by Marek Pszczolka on 05/02/2022.
 *
 * Main workflow taken from
 * https://medium.com/androidxx/an-sticky-button-with-coordinatorlayout-behaviors-bb16b03de4d8
 */
class StickyBottomBehavior(private val anchor: View,
                           private val notStickyMargin: Int): CoordinatorLayout.Behavior<View>() {

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        return (axes == View.SCROLL_AXIS_VERTICAL)
    }

    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int
    ) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)

        val anchorLocation = IntArray(2)
        anchor.getLocationInWindow(anchorLocation)

        val coordBottom = coordinatorLayout.bottom

        // WORKFLOW 1 - sticky to the bottom, then change position at the very end
        if(true){
            //vertical position, cannot scroll over the bottom of the coordinator layout
            child.y = min(anchorLocation[1], coordBottom - child.height).toFloat()

//        //Margins depend on the distance to the bottom
            val diff = max(coordBottom - anchorLocation[1] - child.height, 0)
            val layoutParams = child.layoutParams as MarginLayoutParams
            layoutParams.leftMargin = min(diff, notStickyMargin)
            layoutParams.rightMargin = min(diff, notStickyMargin)
            child.layoutParams = layoutParams
        } else {
            // WORKFLOW 2 - keep at the same position
            //child.y = min(anchorLocation[1], coordBottom - child.height).toFloat()

            val layoutParams = child.layoutParams as MarginLayoutParams
            layoutParams.leftMargin = notStickyMargin
            layoutParams.rightMargin = notStickyMargin
            layoutParams.bottomMargin = notStickyMargin
            child.layoutParams = layoutParams
        }
    }

}