package com.marekpdev.shoppingapp.ui.addresses

import android.util.Log
import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.repository.addresses.AddressesRepository
import com.marekpdev.shoppingapp.repository.orders.OrdersRepository
import com.marekpdev.shoppingapp.repository.user.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
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
                        Log.d("FEO33", "Orders middleware id ${it.id}")
                        requestAction(AddressesAction.Loading)
                        addressesRepository.getAddresses(it.id).collectLatest { orders ->
                            Log.d("FEO33", "get latest middleware ${orders.size}")
                            requestAction(AddressesAction.RefreshData(orders))
                        }
                    }
                }
        }
    }

    override suspend fun process(
        action: AddressesAction,
        currentState: AddressesState,
        requestAction: suspend (AddressesAction) -> Unit,
        requestCommand: suspend (AddressesCommand) -> Unit
    ) {

    }
}