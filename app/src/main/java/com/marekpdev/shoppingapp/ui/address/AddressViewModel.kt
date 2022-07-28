package com.marekpdev.shoppingapp.ui.address

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
class AddressViewModel @AssistedInject constructor(
    store: AddressStore,
    @Assisted private val addressId: Long
) : BaseViewModel<AddressState, AddressAction, AddressCommand>(store) {

    @AssistedFactory
    interface Factory {
        fun create(addressId: Long): AddressViewModel
    }

    @Suppress("UNCHECKED_CAST")
    companion object {

        fun provideFactory(
            assistedFactory: Factory,
            addressId: Long
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(addressId) as T
            }
        }
    }

    init {
        Log.d("FEO33", "INIT AddressViewModel")
        val mode = if(addressId > 0)  Mode.UPDATE else Mode.ADD
        dispatch(AddressAction.Initialize(mode))
        if(mode == Mode.UPDATE){
            dispatch(AddressAction.FetchAddress(addressId))
        }
    }
}