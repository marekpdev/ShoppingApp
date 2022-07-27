package com.marekpdev.shoppingapp.ui.account

import com.marekpdev.shoppingapp.mvi.Command

/**
 * Created by Marek Pszczolka on 20/07/2022.
 */
sealed class AccountCommand: Command{

    object GoToEditProfileScreen: AccountCommand()
    object GoToOrdersScreen: AccountCommand()
    object GoToAddressesScreen: AccountCommand()
    object GoToPaymentMethodsScreen: AccountCommand()
    object GoToSettingsScreen: AccountCommand()
    object GoBackToLoginScreen: AccountCommand()
}