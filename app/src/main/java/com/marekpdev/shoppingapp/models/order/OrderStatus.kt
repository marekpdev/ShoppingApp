package com.marekpdev.shoppingapp.models.order

/**
 * Created by Marek Pszczolka on 02/03/2022.
 */
enum class OrderStatus (val label: String){
    IN_PROGRESS("In Progress"),
    COMPLETE("Complete")
}