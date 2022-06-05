package com.marekpdev.shoppingapp.mvi

import android.util.Log
import com.jakewharton.rxrelay3.BehaviorRelay
import com.jakewharton.rxrelay3.PublishRelay
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

/**
 * Created by Marek Pszczolka on 04/06/2022.
 */
open class Store <S: State, A: Action, C: Command> (
    initialState: S,
    private val middlewares: List<Middleware<S, A, C>>,
    private val reducer: Reducer<S, A>
) {

    private val state = BehaviorRelay.createDefault(initialState)
    private val actions = PublishRelay.create<A>()
    private val commands = PublishRelay.create<C>()

    // todo need to change
    // disposable.add()
    // to
    // disposable += actions ...
    // maybe this library will help??
    // https://github.com/ReactiveX/RxKotlin

    /**
     * We can receive actions from both UI and external sources (such as middleware)
     */
    fun dispatch(action: A){
        actions.accept(action)
    }

    /**
     * We cannot receive actions from UI but only from external sources (such as middleware)
     */
    private fun dispatch(command: C){
        commands.accept(command)
    }

    fun bind(onNewState: (S) -> Unit, onCommand: (C) -> Unit): CompositeDisposable {
        return CompositeDisposable(
            bindState(onNewState),
            bindActions(),
            bindCommands(onCommand)
        )
    }

    private fun bindState(onNewState: (S) -> Unit): Disposable {
        return state.observeOn(AndroidSchedulers.mainThread()).subscribe(onNewState)
    }

    private fun bindActions(): Disposable {
        return actions.withLatestFrom(state) { action, state ->
            for(middleware in middlewares){
                middleware.process(action, state, this::dispatch, this::dispatch)
            }

            reducer.reduce(state, action)
        }
            .distinctUntilChanged()
            .subscribe(state::accept)
    }

    private fun bindCommands(onCommand: (C) -> Unit): Disposable {
        return commands.observeOn(AndroidSchedulers.mainThread()).subscribe(onCommand)
    }

}