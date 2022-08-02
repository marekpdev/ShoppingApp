package com.marekpdev.shoppingapp.ui.checkout

import com.marekpdev.shoppingapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 13/03/2022.
 */

@HiltViewModel
class CheckoutViewModel @Inject constructor(checkoutStore: CheckoutStore): BaseViewModel<CheckoutState, CheckoutAction, CheckoutCommand>(checkoutStore)