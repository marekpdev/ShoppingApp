package com.marekpdev.shoppingapp.ui.account

import com.marekpdev.shoppingapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 13/03/2022.
 */

@HiltViewModel
class AccountViewModel @Inject constructor(accountStore: AccountStore): BaseViewModel<AccountState, AccountAction, AccountCommand>(accountStore)