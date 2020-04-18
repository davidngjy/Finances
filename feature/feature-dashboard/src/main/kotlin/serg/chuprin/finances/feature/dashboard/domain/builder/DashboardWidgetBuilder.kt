package serg.chuprin.finances.feature.dashboard.domain.builder

import kotlinx.coroutines.flow.Flow
import serg.chuprin.finances.core.api.domain.model.DataPeriod
import serg.chuprin.finances.core.api.domain.model.User
import serg.chuprin.finances.feature.dashboard.domain.model.DashboardWidget

/**
 * Created by Sergey Chuprin on 17.04.2020.
 */
interface DashboardWidgetBuilder<W : DashboardWidget> {

    fun build(currentUser: User, currentPeriod: DataPeriod): Flow<W>

}