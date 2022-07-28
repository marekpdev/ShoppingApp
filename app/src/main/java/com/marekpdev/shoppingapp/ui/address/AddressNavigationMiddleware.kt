package com.marekpdev.shoppingapp.ui.address

import com.marekpdev.shoppingapp.mvi.Middleware
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
class AddressNavigationMiddleware @Inject constructor() :
    Middleware<AddressState, AddressAction, AddressCommand> {

    override suspend fun bind(
        coroutineScope: CoroutineScope,
        state: StateFlow<AddressState>,
        requestAction: suspend (AddressAction) -> Unit
    ) {

    }

    override suspend fun process(
        action: AddressAction,
        currentState: AddressState,
        requestAction: suspend (AddressAction) -> Unit,
        requestCommand: suspend (AddressCommand) -> Unit
    ) {
        when(action){
            is AddressAction.AddressAdded -> requestCommand(AddressCommand.GoBackToAddressesScreen)
            is AddressAction.AddressUpdated -> requestCommand(AddressCommand.GoBackToAddressesScreen)
        }
    }
}