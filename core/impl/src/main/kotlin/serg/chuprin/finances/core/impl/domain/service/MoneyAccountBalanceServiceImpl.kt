package serg.chuprin.finances.core.impl.domain.service

import kotlinx.coroutines.flow.*
import serg.chuprin.finances.core.api.domain.model.User
import serg.chuprin.finances.core.api.domain.model.moneyaccount.MoneyAccount
import serg.chuprin.finances.core.api.domain.model.moneyaccount.MoneyAccountBalances
import serg.chuprin.finances.core.api.domain.model.moneyaccount.query.MoneyAccountsQuery
import serg.chuprin.finances.core.api.domain.model.transaction.query.TransactionsQuery
import serg.chuprin.finances.core.api.domain.repository.MoneyAccountRepository
import serg.chuprin.finances.core.api.domain.repository.TransactionRepository
import serg.chuprin.finances.core.api.domain.service.MoneyAccountBalanceService
import serg.chuprin.finances.core.api.extensions.amount
import javax.inject.Inject

/**
 * Created by Sergey Chuprin on 06.05.2020.
 */
internal class MoneyAccountBalanceServiceImpl @Inject constructor(
    private val transactionRepository: TransactionRepository,
    private val moneyAccountRepository: MoneyAccountRepository
) : MoneyAccountBalanceService {

    override fun balancesFlow(user: User): Flow<MoneyAccountBalances> {
        return moneyAccountRepository
            .accountsFlow(MoneyAccountsQuery(ownerId = user.id))
            .flatMapLatest(::calculateBalances)
    }

    private fun calculateBalances(moneyAccounts: List<MoneyAccount>): Flow<MoneyAccountBalances> {
        if (moneyAccounts.isEmpty()) {
            return flowOf(MoneyAccountBalances())
        }
        val balanceFlows = moneyAccounts.map { account ->
            combine(
                flowOf(account),
                transactionRepository
                    .transactionsFlow(
                        TransactionsQuery(moneyAccountIds = setOf(account.id))
                    )
                    .map { transactions -> transactions.amount },
                ::Pair
            )
        }
        return combine(balanceFlows) { array ->
            array.fold(MoneyAccountBalances(), { accountBalances, (account, balance) ->
                accountBalances.add(account, balance)
            })
        }
    }

}