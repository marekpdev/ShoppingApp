package com.marekpdev.shoppingapp.ui.paymentcard

import com.marekpdev.shoppingapp.models.payments.PaymentCard
import com.marekpdev.shoppingapp.models.payments.PaymentCardCreator
import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.repository.paymentmethods.PaymentMethodsRepository
import com.marekpdev.shoppingapp.repository.user.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
class PaymentCardMiddleware @Inject constructor(
    private val userRepository: UserRepository,
    private val paymentMethodsRepository: PaymentMethodsRepository
    )
    : Middleware<PaymentCardState, PaymentCardAction, PaymentCardCommand> {

    override suspend fun bind(
        coroutineScope: CoroutineScope,
        state: StateFlow<PaymentCardState>,
        requestAction: suspend (PaymentCardAction) -> Unit
    ) {

    }

    override suspend fun process(
        action: PaymentCardAction,
        currentState: PaymentCardState,
        requestAction: suspend (PaymentCardAction) -> Unit,
        requestCommand: suspend (PaymentCardCommand) -> Unit
    ) {
        when(action){
            is PaymentCardAction.FetchPaymentCard -> onFetchPaymentCard(action, currentState, requestAction, requestCommand)
            is PaymentCardAction.AddPaymentCard -> onCreatePaymentCard(action, currentState, requestAction, requestCommand)
            is PaymentCardAction.UpdatePaymentCard -> onUpdatePaymentCard(action, currentState, requestAction, requestCommand)
        }
    }

    private suspend fun onFetchPaymentCard(
        action: PaymentCardAction.FetchPaymentCard,
        currentState: PaymentCardState,
        requestAction: suspend (PaymentCardAction) -> Unit,
        requestCommand: suspend (PaymentCardCommand) -> Unit
    ) {
        if(action.paymentCardId > 0){
            requestAction(PaymentCardAction.Loading)
            val paymentCard = paymentMethodsRepository.getPaymentMethod(action.paymentCardId)
            if(paymentCard != null && paymentCard is PaymentCard){
                requestAction(PaymentCardAction.PaymentCardFetched(paymentCard))
            }
        }
    }

    private suspend fun onCreatePaymentCard(
        action: PaymentCardAction.AddPaymentCard,
        currentState: PaymentCardState,
        requestAction: suspend (PaymentCardAction) -> Unit,
        requestCommand: suspend (PaymentCardCommand) -> Unit
    ) {
        val user = userRepository.getUser().value
        if(user != null) {
            requestAction(PaymentCardAction.Loading)
            val paymentCardCreator = PaymentCardCreator(
                cardNumber = currentState.cardNumber,
                cvc = currentState.cvc,
                expiryDate = currentState.cvc
            )
            paymentMethodsRepository.addPaymentCard(user.id, paymentCardCreator)
            requestAction(PaymentCardAction.PaymentCardAdded)
        }
    }

    private suspend fun onUpdatePaymentCard(
        action: PaymentCardAction.UpdatePaymentCard,
        currentState: PaymentCardState,
        requestAction: suspend (PaymentCardAction) -> Unit,
        requestCommand: suspend (PaymentCardCommand) -> Unit
    ) {
        currentState.paymentCardEdited?.let { paymentCard ->
            requestAction(PaymentCardAction.Loading)
            val paymentCardCreator = PaymentCardCreator(
                cardNumber = currentState.cardNumber,
                cvc = currentState.cvc,
                expiryDate = currentState.cvc
            )
            paymentMethodsRepository.updatePaymentCard(paymentCard, paymentCardCreator)
            requestAction(PaymentCardAction.PaymentCardUpdated)
        }
    }

}