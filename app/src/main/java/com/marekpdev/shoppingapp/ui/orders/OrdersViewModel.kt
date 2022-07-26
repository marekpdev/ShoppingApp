package com.marekpdev.shoppingapp.ui.orders

import com.marekpdev.shoppingapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 13/03/2022.
 */

@HiltViewModel
class OrdersViewModel @Inject constructor(ordersStore: OrdersStore): BaseViewModel<OrdersState, OrdersAction, OrdersCommand>(ordersStore)