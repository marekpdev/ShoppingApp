package com.marekpdev.shoppingapp.ui.address

import com.marekpdev.shoppingapp.models.AddressCreator
import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.repository.addresses.AddressesRepository
import com.marekpdev.shoppingapp.repository.user.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
class AddressMiddleware @Inject constructor(
    private val userRepository: UserRepository,
    private val addressesRepository: AddressesRepository
    )
    : Middleware<AddressState, AddressAction, AddressCommand> {

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
            is AddressAction.FetchAddress -> onFetchAddress(action, currentState, requestAction, requestCommand)
            is AddressAction.AddAddress -> onCreateAddress(action, currentState, requestAction, requestCommand)
            is AddressAction.UpdateAddress -> onUpdateAddress(action, currentState, requestAction, requestCommand)
        }
    }

    private suspend fun onFetchAddress(
        action: AddressAction.FetchAddress,
        currentState: AddressState,
        requestAction: suspend (AddressAction) -> Unit,
        requestCommand: suspend (AddressCommand) -> Unit
    ) {
        if(action.addressId > 0){
            requestAction(AddressAction.Loading)
            val address = addressesRepository.getAddress(action.addressId)
            if(address != null){
                requestAction(AddressAction.AddressFetched(address))
            }
        }
    }

    private suspend fun onCreateAddress(
        action: AddressAction.AddAddress,
        currentState: AddressState,
        requestAction: suspend (AddressAction) -> Unit,
        requestCommand: suspend (AddressCommand) -> Unit
    ) {
        val user = userRepository.getUser().value
        if(user != null) {
            requestAction(AddressAction.Loading)
            val addressCreator = AddressCreator(
                line1 = currentState.line1,
                line2 = currentState.line2,
                postcode = currentState.postcode,
                city = currentState.city,
                country = currentState.country
            )
            addressesRepository.addAddress(user.id, addressCreator)
            requestAction(AddressAction.AddressAdded)
        }
    }

    private suspend fun onUpdateAddress(
        action: AddressAction.UpdateAddress,
        currentState: AddressState,
        requestAction: suspend (AddressAction) -> Unit,
        requestCommand: suspend (AddressCommand) -> Unit
    ) {
        currentState.addressEdited?.let { address ->
            requestAction(AddressAction.Loading)
            val addressCreator = AddressCreator(
                line1 = currentState.line1,
                line2 = currentState.line2,
                postcode = currentState.postcode,
                city = currentState.city,
                country = currentState.country
            )
            addressesRepository.updateAddress(address, addressCreator)
            requestAction(AddressAction.AddressUpdated)
        }
    }

}