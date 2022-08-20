package com.marekpdev.shoppingapp.ui.addresses

import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.repository.addresses.AddressesRepository
import com.marekpdev.shoppingapp.repository.user.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
class AddressesMiddleware @Inject constructor(
    private val userRepository: UserRepository,
    private val addressesRepository: AddressesRepository
    )
    : Middleware<AddressesState, AddressesAction, AddressesCommand> {

    override suspend fun bind(
        coroutineScope: CoroutineScope,
        state: StateFlow<AddressesState>,
        requestAction: suspend (AddressesAction) -> Unit
    ) {
        userRepository.getUser()
            .filterNotNull()
            .onEach { requestAction(AddressesAction.Loading) } // TODO use onStart or onEach?
            .flatMapLatest { user -> addressesRepository.getAddresses(user.id) }
            .onEach { addresses -> requestAction(AddressesAction.RefreshData(addresses)) }
            .launchIn(coroutineScope)
    }

    override suspend fun process(
        action: AddressesAction,
        currentState: AddressesState,
        requestAction: suspend (AddressesAction) -> Unit,
        requestCommand: suspend (AddressesCommand) -> Unit
    ) {

    }
}