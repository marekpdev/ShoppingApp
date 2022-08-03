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
        coroutineScope.launch {
            // todo figure out a better way to chain these flows
            // userRepository.getUser() & addressesRepository.getOrders(it.id)
            userRepository.getUser()
                .collectLatest { user ->
                    user?.let {
                        requestAction(AddressesAction.Loading)
                        addressesRepository.getAddresses(it.id).collectLatest { addresses ->
                            requestAction(AddressesAction.RefreshData(addresses))
                        }
                    }
                }
        }

        // TODO use this instead? check if this works
        userRepository.getUser()
            .onEach { requestAction(AddressesAction.Loading) } // TODO use onStart on onEach?
            .filterNotNull()
            .flatMapLatest { user -> addressesRepository.getAddresses(user.id) } // TODO which flatmap type to use?
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