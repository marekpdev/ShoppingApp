package com.marekpdev.shoppingapp.ui.account

import com.marekpdev.shoppingapp.mvi.Middleware
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
class AccountNavigationMiddleware @Inject constructor() :
    Middleware<AccountState, AccountAction, AccountCommand> {

    override suspend fun bind(
        coroutineScope: CoroutineScope,
        state: StateFlow<AccountState>,
        requestAction: suspend (AccountAction) -> Unit
    ) {

    }

    override suspend fun process(
        action: AccountAction,
        currentState: AccountState,
        requestAction: suspend (AccountAction) -> Unit,
        requestCommand: suspend (AccountCommand) -> Unit
    ) {
        when(action){
            is AccountAction.EditProfileClicked -> requestCommand(AccountCommand.GoToEditProfileScreen)
            is AccountAction.OrdersClicked -> requestCommand(AccountCommand.GoToOrdersScreen)
            is AccountAction.AddressesClicked -> requestCommand(AccountCommand.GoToAddressesScreen)
            is AccountAction.PaymentMethodsClicked -> requestCommand(AccountCommand.GoToPaymentMethodsScreen)
            is AccountAction.SettingsClicked -> requestCommand(AccountCommand.GoToSettingsScreen)
            is AccountAction.LogoutClicked -> requestCommand(AccountCommand.GoBackToLoginScreen)
            else -> {}
        }
    }
}