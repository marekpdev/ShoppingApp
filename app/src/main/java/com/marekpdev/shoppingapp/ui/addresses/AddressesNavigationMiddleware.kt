package com.marekpdev.shoppingapp.ui.addresses

import com.marekpdev.shoppingapp.mvi.Middleware
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
class AddressesNavigationMiddleware @Inject constructor() :
    Middleware<AddressesState, AddressesAction, AddressesCommand> {

    override suspend fun bind(
        coroutineScope: CoroutineScope,
        state: StateFlow<AddressesState>,
        requestAction: suspend (AddressesAction) -> Unit
    ) {

    }

    override suspend fun process(
        action: AddressesAction,
        currentState: AddressesState,
        requestAction: suspend (AddressesAction) -> Unit,
        requestCommand: suspend (AddressesCommand) -> Unit
    ) {
        when(action){
            is AddressesAction.AddressClicked -> requestCommand(AddressesCommand.GoToAddressDetails(action.address))
        }
    }
}