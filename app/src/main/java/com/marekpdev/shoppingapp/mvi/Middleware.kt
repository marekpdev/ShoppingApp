package com.marekpdev.shoppingapp.mvi

import com.jakewharton.rxrelay3.PublishRelay
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable

/**
 * Created by Marek Pszczolka on 04/06/2022.
 */
interface Middleware<S: State, A: Action, C: Command> {

    fun bind(actions: PublishRelay<A>, commands: PublishRelay<C>, state: Flowable<S>): Observable<A>

}