package com.marekpdev.shoppingapp.ui.addresses

import com.marekpdev.shoppingapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 13/03/2022.
 */

@HiltViewModel
class AddressesViewModel @Inject constructor(addressesStore: AddressesStore): BaseViewModel<AddressesState, AddressesAction, AddressesCommand>(addressesStore)