package com.marekpdev.shoppingapp.ui.home

import com.marekpdev.shoppingapp.mvi.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 13/03/2022.
 */

@HiltViewModel
class HomeViewModel @Inject constructor(store: HomeStore): BaseViewModel<HomeState, HomeAction, HomeCommand>(store)