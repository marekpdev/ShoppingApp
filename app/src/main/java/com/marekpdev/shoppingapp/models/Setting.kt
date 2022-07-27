package com.marekpdev.shoppingapp.models

/**
 * Created by Marek Pszczolka on 27/07/2022.
 */
sealed class Setting {

    data class Notifications(val enabled: Boolean): Setting()

}