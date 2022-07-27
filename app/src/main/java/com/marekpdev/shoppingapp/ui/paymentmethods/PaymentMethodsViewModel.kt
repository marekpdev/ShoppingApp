package com.marekpdev.shoppingapp.ui.paymentmethods

import com.marekpdev.shoppingapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 13/03/2022.
 */

@HiltViewModel
class PaymentMethodsViewModel @Inject constructor(paymentMethodsStore: PaymentMethodsStore): BaseViewModel<PaymentMethodsState, PaymentMethodsAction, PaymentMethodsCommand>(paymentMethodsStore)