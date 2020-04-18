package serg.chuprin.finances.feature.dashboard.presentation.model.store

import serg.chuprin.finances.core.api.domain.model.User
import serg.chuprin.finances.feature.dashboard.presentation.model.cells.DashboardWidgetCell

/**
 * Created by Sergey Chuprin on 16.04.2020.
 */
sealed class DashboardEffect {

    class WidgetCellsUpdated(
        val widgetCells: List<DashboardWidgetCell>
    ) : DashboardEffect()

    class UserUpdated(
        val user: User
    ) : DashboardEffect()

}