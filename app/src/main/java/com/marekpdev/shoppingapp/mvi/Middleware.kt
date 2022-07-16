package com.marekpdev.shoppingapp.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

/**
 * Created by Marek Pszczolka on 04/06/2022.
 */
interface Middleware<S: State, A: Action, C: Command> {

    suspend fun bind(coroutineScope: CoroutineScope,
                     state: StateFlow<S>,
                     requestAction: suspend (A) -> Unit)

    suspend fun process(action: A,
                        currentState: S,
                        requestAction: suspend (A) -> Unit,
                        requestCommand: suspend (C) -> Unit
    )


}