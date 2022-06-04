package com.marekpdev.shoppingapp.mvi

/**
 * Created by Marek Pszczolka on 04/06/2022.
 */
interface Middleware<S: ViewState, A: Action> {

    fun dispatch(action: A)

}