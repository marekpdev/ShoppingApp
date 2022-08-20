package com.marekpdev.shoppingapp.ui.paymentmethods

import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.repository.paymentmethods.PaymentMethodsRepository
import com.marekpdev.shoppingapp.repository.user.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
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
        userRepository.getUser()
            .filterNotNull()
            .onEach { requestAction(PaymentMethodsAction.Loading) }
            .flatMapLatest { user -> paymentMethodsRepository.getPaymentMethods(user.id) }
            .onEach { paymentMethods -> requestAction(PaymentMethodsAction.RefreshData(paymentMethods)) }
            .launchIn(coroutineScope)
    }

    override suspend fun process(
        action: PaymentMethodsAction,
        currentState: PaymentMethodsState,
        requestAction: suspend (PaymentMethodsAction) -> Unit,
        requestCommand: suspend (PaymentMethodsCommand) -> Unit
    ) {

    }
}