package serg.chuprin.finances.feature.transaction.presentation.model.store

import serg.chuprin.finances.core.mvi.reducer.StoreStateReducer

/**
 * Created by Sergey Chuprin on 02.01.2021.
 */
class TransactionStateReducer : StoreStateReducer<TransactionEffect, TransactionState> {

    override fun invoke(
        what: TransactionEffect,
        state: TransactionState
    ): TransactionState {
        TODO("Not yet implemented")
    }

}