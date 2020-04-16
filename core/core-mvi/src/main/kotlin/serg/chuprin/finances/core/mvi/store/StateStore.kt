package serg.chuprin.finances.core.mvi.store

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow


/**
 * Created by Sergey Chuprin on 09.08.2019.
 *
 * Store it is a component which runs a events loop and contains a state.
 *
 * For detailed explanation
 * @see [BaseStateStore]
 *
 * [I] - type of intents this store handles.
 * [S] - type of state.
 * [E] - type of single-time events.
 */
interface StateStore<I, S, E> {

    val state: S

    fun dispatch(intent: I)

    fun stateFlow(): Flow<S>

    fun eventsFlow(): Flow<E>

    /**
     * Start a store's loop.
     * After this function call store is ready to receiving intents.
     */
    fun start(intentsFlow: Flow<I>, scope: CoroutineScope)

}