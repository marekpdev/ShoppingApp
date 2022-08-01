package com.marekpdev.shoppingapp.ui.checkout

import android.util.Log
import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.repository.addresses.AddressesRepository
import com.marekpdev.shoppingapp.repository.basket.BasketRepository
import com.marekpdev.shoppingapp.repository.orders.OrdersRepository
import com.marekpdev.shoppingapp.repository.paymentmethods.PaymentMethodsRepository
import com.marekpdev.shoppingapp.repository.user.UserRepository
import com.marekpdev.shoppingapp.ui.addresses.AddressesAction
import com.marekpdev.shoppingapp.ui.paymentmethods.PaymentMethodsAction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
class CheckoutMiddleware @Inject constructor(private val userRepository: UserRepository,
                                             private val basketRepository: BasketRepository,
                                             private val addressesRepository: AddressesRepository,
                                             private val paymentMethodsRepository: PaymentMethodsRepository
    ) :
    Middleware<CheckoutState, CheckoutAction, CheckoutCommand> {

    override suspend fun bind(
        coroutineScope: CoroutineScope,
        state: StateFlow<CheckoutState>,
        requestAction: suspend (CheckoutAction) -> Unit
    ) {
        coroutineScope.launch {
            // todo figure out a better way to chain these flows
            // userRepository.getUser() & addressesRepository.getOrders(it.id)
            userRepository.getUser()
                .collectLatest { user ->
                    user?.let {
                        Log.d("FEO66", "loading payment methods 111")
                        requestAction(CheckoutAction.LoadingDeliveryAddresses)
                        addressesRepository.getAddresses(it.id).collectLatest { addresses ->
                            requestAction(CheckoutAction.RefreshDeliveryAddresses(addresses))
                        }
                    }
                }
        }
        coroutineScope.launch {
            userRepository.getUser()
                .collectLatest { user ->
                    user?.let {
                        requestAction(CheckoutAction.LoadingPaymentMethods)
                        Log.d("FEO66", "loading payment methods 1")
                        paymentMethodsRepository.getPaymentMethods(it.id).collectLatest { paymentMethods ->
                            Log.d("FEO66", "loading payment methods 2: ${paymentMethods}")
                            requestAction(CheckoutAction.RefreshPaymentMethods(paymentMethods))
                        }
                    }
                }
        }
    }

    override suspend fun process(
        action: CheckoutAction,
        currentState: CheckoutState,
        requestAction: suspend (CheckoutAction) -> Unit,
        requestCommand: suspend (CheckoutCommand) -> Unit
    ) {

    }
}