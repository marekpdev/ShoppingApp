package com.marekpdev.shoppingapp.mvi

import io.reactivex.rxjava3.subjects.PublishSubject

/**
 * Created by Marek Pszczolka on 04/06/2022.
 */
interface Middleware<S: State, A: Action, C: Command> {

    fun process(action: A,
                currentState: S,
                requestAction: (A) -> Unit,
                requestCommand: (C) -> Unit
    )

}