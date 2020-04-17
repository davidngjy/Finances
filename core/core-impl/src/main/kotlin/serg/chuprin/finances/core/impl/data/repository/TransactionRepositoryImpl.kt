package serg.chuprin.finances.core.impl.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import serg.chuprin.finances.core.api.domain.model.DataPeriod
import serg.chuprin.finances.core.api.domain.model.Id
import serg.chuprin.finances.core.api.domain.model.Transaction
import serg.chuprin.finances.core.api.domain.model.TransactionType
import serg.chuprin.finances.core.api.domain.repository.TransactionRepository
import serg.chuprin.finances.core.impl.data.database.firebase.datasource.FirebaseTransactionDataSource
import serg.chuprin.finances.core.impl.data.mapper.TransactionMapper
import javax.inject.Inject

/**
 * Created by Sergey Chuprin on 04.04.2020.
 */
internal class TransactionRepositoryImpl @Inject constructor(
    private val mapper: TransactionMapper,
    private val firebaseDataSource: FirebaseTransactionDataSource
) : TransactionRepository {

    private companion object {
        private val USER_TRANSACTION_TYPES = listOf(TransactionType.INCOME, TransactionType.EXPENSE)
    }

    override fun createTransaction(transaction: Transaction) {
        firebaseDataSource.createTransaction(transaction)
    }

    override fun userTransactionsFlow(userId: Id, dataPeriod: DataPeriod): Flow<List<Transaction>> {
        return firebaseDataSource
            .userTransactionsFlow(userId)
            .map { transactions ->
                transactions
                    .mapNotNull(mapper)
                    .filter { transaction ->
                        transaction.type in USER_TRANSACTION_TYPES
                                && transaction.date in dataPeriod
                    }
            }
            .flowOn(Dispatchers.Default)
    }

    override fun userTransactionsFlow(userId: Id): Flow<List<Transaction>> {
        return firebaseDataSource
            .userTransactionsFlow(userId)
            .map { transactions ->
                transactions
                    .mapNotNull(mapper)
                    .filter { transaction ->
                        transaction.type in USER_TRANSACTION_TYPES
                    }
            }
            .flowOn(Dispatchers.Default)
    }

}