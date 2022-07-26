package com.marekpdev.shoppingapp.ui.orders

import org.joda.time.DateTime

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
enum class OrderGroup(val label: String) {
    TODAY("Today"),
    YESTERDAY("Yesterday"),
    THIS_WEEK("This week"),
    LAST_WEEK("Last week"),
    LAST_MONTH("Last month"),
    LATER("Later");


    companion object {
        fun getOrderGroup(test: DateTime, now: DateTime): OrderGroup{
            if(test.year < now.year) return LATER

            return when {
                test.dayOfYear == now.dayOfYear -> TODAY
                test.dayOfYear == now.dayOfYear - 1 -> YESTERDAY
                test.weekOfWeekyear == now.weekOfWeekyear -> THIS_WEEK
                test.weekOfWeekyear == now.weekOfWeekyear - 1 -> LAST_WEEK
                test.monthOfYear == now.monthOfYear -1 -> LAST_MONTH
                else -> LATER
            }
        }
    }

}