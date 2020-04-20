package serg.chuprin.finances.core.impl.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import serg.chuprin.finances.core.api.domain.model.DataPeriod
import serg.chuprin.finances.core.api.domain.model.Id
import serg.chuprin.finances.core.api.domain.model.Transaction
import serg.chuprin.finances.core.api.domain.repository.TransactionRepository
import serg.chuprin.finances.core.impl.data.datasource.firebase.FirebaseTransactionDataSource
import serg.chuprin.finances.core.impl.data.mapper.transaction.FirebaseTransactionMapper
import javax.inject.Inject

/**
 * Created by Sergey Chuprin on 04.04.2020.
 */
internal class TransactionRepositoryImpl @Inject constructor(
    private val mapper: FirebaseTransactionMapper,
    private val firebaseDataSource: FirebaseTransactionDataSource
) : TransactionRepository {

    override fun createTransaction(transaction: Transaction) {
        firebaseDataSource.createTransaction(transaction)
    }

    override fun userTransactionsFlow(userId: Id, dataPeriod: DataPeriod): Flow<List<Transaction>> {
        return firebaseDataSource
            .userTransactionsFlow(userId)
            .map { transactions ->
                transactions
                    .mapNotNull(mapper::mapFromSnapshot)
                    .filter { transaction -> transaction.dateTime in dataPeriod }
            }
            .flowOn(Dispatchers.Default)
    }

    override fun userTransactionsFlow(userId: Id): Flow<List<Transaction>> {
        return firebaseDataSource
            .userTransactionsFlow(userId)
            .map { transactions -> transactions.mapNotNull(mapper::mapFromSnapshot) }
            .flowOn(Dispatchers.Default)
    }

}