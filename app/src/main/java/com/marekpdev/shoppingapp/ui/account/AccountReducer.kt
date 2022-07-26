package com.marekpdev.shoppingapp.ui.account

import com.marekpdev.shoppingapp.mvi.Reducer

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
class AccountReducer: Reducer<AccountState, AccountAction> {

    override fun reduce(currentState: AccountState, action: AccountAction): AccountState {
        return when (action){
            is AccountAction.RefreshUserData -> currentState.copy(email = action.email)
            else -> {
                currentState
            }
        }
    }

}