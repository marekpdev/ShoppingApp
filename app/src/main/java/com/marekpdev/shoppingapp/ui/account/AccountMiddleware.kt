package com.marekpdev.shoppingapp.ui.account

import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.repository.user.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
class AccountMiddleware @Inject constructor(val userRepository: UserRepository) :
    Middleware<AccountState, AccountAction, AccountCommand> {

    override suspend fun bind(
        coroutineScope: CoroutineScope,
        state: StateFlow<AccountState>,
        requestAction: suspend (AccountAction) -> Unit
    ) {
        userRepository.getUser()
            .onEach { user -> requestAction(AccountAction.RefreshUserData(user?.name ?: "N/A")) }
            .launchIn(coroutineScope)
    }

    override suspend fun process(
        action: AccountAction,
        currentState: AccountState,
        requestAction: suspend (AccountAction) -> Unit,
        requestCommand: suspend (AccountCommand) -> Unit
    ) {

    }
}