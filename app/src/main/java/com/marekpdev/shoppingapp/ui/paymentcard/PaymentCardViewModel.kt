package com.marekpdev.shoppingapp.ui.paymentcard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marekpdev.shoppingapp.ui.base.BaseViewModel
import com.marekpdev.shoppingapp.ui.order.OrderAction
import com.marekpdev.shoppingapp.ui.order.OrderCommand
import com.marekpdev.shoppingapp.ui.order.OrderState
import com.marekpdev.shoppingapp.ui.order.OrderStore
import com.marekpdev.shoppingapp.ui.order.OrderViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 13/03/2022.
 */
class PaymentCardViewModel @AssistedInject constructor(
    store: PaymentCardStore,
    @Assisted private val paymentCardId: Long
) : BaseViewModel<PaymentCardState, PaymentCardAction, PaymentCardCommand>(store) {

    @AssistedFactory
    interface Factory {
        fun create(paymentCardId: Long): PaymentCardViewModel
    }

    @Suppress("UNCHECKED_CAST")
    companion object {

        fun provideFactory(
            assistedFactory: Factory,
            paymentCardId: Long
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(paymentCardId) as T
            }
        }
    }

    init {
        val mode = if(paymentCardId > 0)  Mode.UPDATE else Mode.ADD
        Log.d("FEO55", "INIT PaymentCardViewModel $mode")
        dispatch(PaymentCardAction.Initialize(mode))
        if(mode == Mode.UPDATE){
            dispatch(PaymentCardAction.FetchPaymentCard(paymentCardId))
        }
    }
}