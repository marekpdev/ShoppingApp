package com.marekpdev.shoppingapp.ui.account

import com.marekpdev.shoppingapp.mvi.Action

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
sealed class AccountAction : Action {

    object EditProfileClicked: AccountAction()
    object OrdersClicked: AccountAction()
    object AddressesClicked: AccountAction()
    object PaymentMethodsClicked: AccountAction()
    object SettingsClicked: AccountAction()
    object LogoutClicked: AccountAction()

    data class RefreshUserData(val email: String): AccountAction()

}