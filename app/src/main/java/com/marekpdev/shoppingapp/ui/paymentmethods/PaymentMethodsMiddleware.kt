package com.marekpdev.shoppingapp.ui.paymentmethods

import com.marekpdev.shoppingapp.mvi.Middleware
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
class PaymentMethodsMiddleware @Inject constructor(
    private val userRepository: UserRepository,
    private val paymentMethodsRepository: PaymentMethodsRepository
    )
    : Middleware<PaymentMethodsState, PaymentMethodsAction, PaymentMethodsCommand> {

    override suspend fun bind(
        coroutineScope: CoroutineScope,
        state: StateFlow<PaymentMethodsState>,
        requestAction: suspend (PaymentMethodsAction) -> Unit
    ) {
        coroutineScope.launch {
            // todo figure out a better way to chain these flows
            // userRepository.getUser() & paymentMethodsRepository.getOrders(it.id)
            userRepository.getUser()
                .collectLatest { user ->
                    user?.let {
                        requestAction(PaymentMethodsAction.Loading)
                        paymentMethodsRepository.getPaymentMethods(it.id).collectLatest { paymentMethods ->
                            requestAction(PaymentMethodsAction.RefreshData(paymentMethods))
                        }
                    }
                }
        }
    }

    override suspend fun process(
        action: PaymentMethodsAction,
        currentState: PaymentMethodsState,
        requestAction: suspend (PaymentMethodsAction) -> Unit,
        requestCommand: suspend (PaymentMethodsCommand) -> Unit
    ) {

    }
}