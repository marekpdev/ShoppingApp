package com.marekpdev.shoppingapp.mvi

/**
 * Created by Marek Pszczolka on 05/06/2022.
 */
// todo
// for the moment we are using it in ViewModel
// but probably we need to change its name or change where we use it
// because by 'View' we are basically saying that it should be Fragment/Activity or something
interface MviView<S: State, A: Action, C: Command> {

    fun onNewState(state: S)

    fun onCommand(command: C)
}