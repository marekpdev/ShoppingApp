package com.marekpdev.shoppingapp.mvi

/**
 * Created by Marek Pszczolka on 04/06/2022.
 */
interface Middleware<S: State, A: Action, C: Command> {

    fun process(action: A,
                currentState: S,
                onRequestAction: (A) -> Unit,
                onRequestCommand: (C) -> Unit
    )

}