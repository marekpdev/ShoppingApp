package com.marekpdev.shoppingapp.ui.account

import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.mvi.Store

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
class AccountStore (
    initialState: AccountState,
    middlewares: List<Middleware<AccountState, AccountAction, AccountCommand>>,
    reducer: AccountReducer
) : Store<AccountState, AccountAction, AccountCommand>(initialState, middlewares, reducer)