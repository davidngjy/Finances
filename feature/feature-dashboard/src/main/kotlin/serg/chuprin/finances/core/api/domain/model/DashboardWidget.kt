package serg.chuprin.finances.core.api.domain.model

import serg.chuprin.finances.core.api.domain.model.category.TransactionCategoryWithParent
import serg.chuprin.finances.core.api.domain.model.period.DataPeriod
import serg.chuprin.finances.core.api.domain.model.transaction.Transaction
import java.math.BigDecimal
import java.util.*

/**
 * Created by Sergey Chuprin on 17.04.2020.
 */
sealed class DashboardWidget(val type: Type) {

    enum class Type(val order: Int) {
        MONEY_ACCOUNTS(1),
        HEADER(2),
        RECENT_TRANSACTIONS(3),
        CATEGORIES(4)
    }

    data class MoneyAccounts(
        val moneyAccountBalances: MoneyAccountBalances
    ) : DashboardWidget(type = Type.MONEY_ACCOUNTS)

    /**
     * Contains current period, balance and money statistic.
     */
    data class Header(
        val dataPeriod: DataPeriod,
        val balance: BigDecimal,
        val currency: Currency,
        val currentPeriodIncomes: BigDecimal,
        val currentPeriodExpenses: BigDecimal
    ) : DashboardWidget(type = Type.HEADER)

    data class RecentTransactions(
        val currency: Currency,
        val transactionWithCategoryMap: Map<Transaction, TransactionCategoryWithParent?>
    ) : DashboardWidget(type = Type.RECENT_TRANSACTIONS)

    data class Categories(
        val currency: Currency,
        val pages: List<DashboardCategoriesWidgetPage>
    ) : DashboardWidget(type = Type.CATEGORIES)

}