package com.marekpdev.shoppingapp.ui.address

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marekpdev.shoppingapp.ui.base.BaseViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

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
        val mode = if(addressId > 0)  Mode.UPDATE else Mode.ADD
        dispatch(AddressAction.Initialize(mode))
        if(mode == Mode.UPDATE){
            dispatch(AddressAction.FetchAddress(addressId))
        }
    }
}