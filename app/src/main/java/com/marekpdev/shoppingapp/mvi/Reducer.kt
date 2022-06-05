package com.marekpdev.shoppingapp.mvi

/**
 * Created by Marek Pszczolka on 04/06/2022.
 */
interface Reducer<S: State, A: Action> {

    fun reduce(currentState: S, action: A): S

}