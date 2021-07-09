package com.marekpdev.shoppingapp.views

import android.content.res.ColorStateList

/**
 * Created by Marek Pszczolka on 26/04/2021.
 * (some notes from stack overflow)
 *
 * IMPORTANT
 * We need to understand that if we return "enabled" state as a first one, it will override
 * other states that typically occur while a button is enabled. It is better to put the
 * 'enabled' state as the last one (or even better instead of 'enabled' just use the empty/default
 * item as last)
 * If we want to get some colors for disabled, unfocused, unchecked states we can just negate
 * the states e.g. to get {disabled} we use {-enabled}
 *
 * For basic states for a button that does not retain state (NOT a toggle/checkbox):
 * 1. {pressed}
 * 2. {focused}
 * 3. {-enabled}
 * 4. {} (default - meaning 'all other cases')
 *
 * For a toggle it might be
 * 1. {checked, pressed}
 * 2. {pressed}
 * 3. {checked, focused}
 * 4. {focused}
 * 5. {checked}
 * 6. {-enabled}
 * 7. {} (default - meaning 'all other cases')
 *
 * Or for a toggle that ignores focus
 * 1. {checked, pressed}
 * 2. {pressed}
 * 3. {checked}
 * 4. {-enabled}
 * 5. {} (default - meaning 'all other cases')
 *
 * So we need to remember that the order of the states that we give is important
 *
 * For our needs we simply need to detect if the item is checked or not
 * 1. {checked}
 * 2. {-enabled}
 * 3. {} (default - meaning 'all other cases')
 * (we might also include some disabled state which is {-enabled}
 */

enum class ViewState(val stateResArray: IntArray) {
    CHECKED(intArrayOf(android.R.attr.state_checked)),
    DISABLED(intArrayOf(-android.R.attr.state_enabled)),
    DEFAULT(intArrayOf())
    // can add more later on if needed
}

object ColorStateListHelper {

    fun colorStateListOf(vararg mapping: Pair<ViewState, Int>): ColorStateList {
        val (states, colors) = mapping.unzip()
        return ColorStateList(
            states.map { it.stateResArray }.toTypedArray(),
            colors.toIntArray()
        )
    }

}

