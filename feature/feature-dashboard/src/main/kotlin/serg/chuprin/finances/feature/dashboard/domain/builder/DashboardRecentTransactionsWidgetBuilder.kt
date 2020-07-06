package serg.chuprin.finances.feature.dashboard.domain.builder

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import serg.chuprin.finances.core.api.domain.model.User
import serg.chuprin.finances.core.api.domain.model.period.DataPeriod
import serg.chuprin.finances.core.api.domain.service.TransactionCategoryRetrieverService
import serg.chuprin.finances.feature.dashboard.domain.model.DashboardWidget
import serg.chuprin.finances.feature.dashboard.domain.repository.DashboardDataPeriodRepository
import javax.inject.Inject

/**
 * Created by Sergey Chuprin on 20.04.2020.
 */
class DashboardRecentTransactionsWidgetBuilder @Inject constructor(
    private val dataPeriodRepository: DashboardDataPeriodRepository,
    private val transactionCategoryRetrieverService: TransactionCategoryRetrieverService
) : DashboardWidgetBuilder<DashboardWidget.RecentTransactions> {

    private companion object {
        private const val RECENT_TRANSACTIONS_COUNT = 4
    }

    override fun build(
        currentUser: User,
        currentPeriod: DataPeriod
    ): Flow<DashboardWidget.RecentTransactions>? {
        if (dataPeriodRepository.defaultDataPeriod != currentPeriod) {
            return null
        }
        return transactionCategoryRetrieverService
            .userTransactionsFlow(
                userId = currentUser.id,
                endDate = currentPeriod.endDate,
                startDate = currentPeriod.startDate,
                count = RECENT_TRANSACTIONS_COUNT
            )
            .map { map -> DashboardWidget.RecentTransactions(currentUser.defaultCurrency, map) }
    }

}