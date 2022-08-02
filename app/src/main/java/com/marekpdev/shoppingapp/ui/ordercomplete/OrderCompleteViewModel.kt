package com.marekpdev.shoppingapp.ui.ordercomplete

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marekpdev.shoppingapp.ui.base.BaseViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

/**
 * Created by Marek Pszczolka on 11/07/2021.
 */
class OrderCompleteViewModel @AssistedInject constructor(
    store: OrderCompleteStore,
    @Assisted private val orderId: Long,
) : BaseViewModel<OrderCompleteState, OrderCompleteAction, OrderCompleteCommand>(store) {

    @AssistedFactory
    interface Factory {
        fun create(productId: Long): OrderCompleteViewModel
    }

    @Suppress("UNCHECKED_CAST")
    companion object {

        fun provideFactory(
            assistedFactory: Factory,
            orderId: Long
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(orderId) as T
            }
        }
    }

    init {
        Log.d("FEO33", "INIT OrderCompleteViewModel")
        dispatch(OrderCompleteAction.FetchOrder(orderId))
    }
}