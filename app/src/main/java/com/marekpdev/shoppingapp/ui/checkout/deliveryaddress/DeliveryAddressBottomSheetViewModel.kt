package com.marekpdev.shoppingapp.ui.checkout.deliveryaddress

import com.marekpdev.shoppingapp.ui.base.BaseViewModel
import com.marekpdev.shoppingapp.ui.checkout.CheckoutAction
import com.marekpdev.shoppingapp.ui.checkout.CheckoutCommand
import com.marekpdev.shoppingapp.ui.checkout.CheckoutState
import com.marekpdev.shoppingapp.ui.checkout.CheckoutStore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 13/03/2022.
 */

@HiltViewModel
class DeliveryAddressBottomSheetViewModel @Inject constructor(checkoutStore: CheckoutStore): BaseViewModel<CheckoutState, CheckoutAction, CheckoutCommand>(checkoutStore)