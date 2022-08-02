package com.marekpdev.shoppingapp.ui.checkout

import com.marekpdev.shoppingapp.models.order.OrderCreator
import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.repository.addresses.AddressesRepository
import com.marekpdev.shoppingapp.repository.basket.BasketRepository
import com.marekpdev.shoppingapp.repository.orders.OrdersRepository
import com.marekpdev.shoppingapp.repository.paymentmethods.PaymentMethodsRepository
import com.marekpdev.shoppingapp.repository.user.UserRepository
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
                                             private val paymentMethodsRepository: PaymentMethodsRepository,
                                             private val ordersRepository: OrdersRepository
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
                        paymentMethodsRepository.getPaymentMethods(it.id).collectLatest { paymentMethods ->
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
        when(action) {
            is CheckoutAction.PlaceOrder -> onPlaceOrder(action, currentState, requestAction, requestCommand)
        }
    }

    private suspend fun onPlaceOrder(
        action: CheckoutAction.PlaceOrder,
        currentState: CheckoutState,
        requestAction: suspend (CheckoutAction) -> Unit,
        requestCommand: suspend (CheckoutCommand) -> Unit
    ) {
        val userId = userRepository.getUser().value?.id ?: return
        if(currentState.selectedDeliveryAddress == null) return
        if(currentState.selectedPaymentMethod == null) return

        val orderCreator = OrderCreator(
            userId = userId,
            products = basketRepository.observeBasketProducts().value,
            deliveryAddress = currentState.selectedDeliveryAddress,
            paymentMethod = currentState.selectedPaymentMethod
        )
        val newOrder = ordersRepository.placeOrder(orderCreator)
        requestCommand(CheckoutCommand.GoToOrderCompleteScreen(newOrder.id))
    }
}