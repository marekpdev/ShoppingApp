package com.marekpdev.shoppingapp.mvi

/**
 * Created by Marek Pszczolka on 05/06/2022.
 */
interface MviView<S: State, C: Command> {

    fun render(state: S)

    fun onCommand(command: C)
}