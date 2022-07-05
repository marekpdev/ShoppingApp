package com.marekpdev.shoppingapp.mvi

import io.reactivex.rxjava3.core.Observable

/**
 * Created by Marek Pszczolka on 04/06/2022.
 */
interface Middleware<S: State, A: Action, C: Command> {

    fun bind(actions: Observable<A>,
             state: Observable<S>,
             requestAction: (A) -> Unit,
             requestCommand: (C) -> Unit
    ): Observable<A>

}