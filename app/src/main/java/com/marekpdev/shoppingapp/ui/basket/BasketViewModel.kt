package com.marekpdev.shoppingapp.ui.basket

import com.marekpdev.shoppingapp.mvi.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 13/03/2022.
 */

@HiltViewModel
class BasketViewModel @Inject constructor(store: BasketStore): BaseViewModel<BasketState, BasketAction, BasketCommand>(store)