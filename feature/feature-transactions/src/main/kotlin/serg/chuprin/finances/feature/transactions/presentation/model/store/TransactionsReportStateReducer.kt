package serg.chuprin.finances.feature.transactions.presentation.model.store

import serg.chuprin.finances.core.mvi.reducer.StoreStateReducer

/**
 * Created by Sergey Chuprin on 12.05.2020.
 */
class TransactionsReportStateReducer :
    StoreStateReducer<TransactionsReportEffect, TransactionsReportState> {

    override fun invoke(
        what: TransactionsReportEffect,
        state: TransactionsReportState
    ): TransactionsReportState {
        TODO("Not yet implemented")
    }

}