package serg.chuprin.finances.feature.dashboard.setup.presentation.domain.model

/**
 * Created by Sergey Chuprin on 29.11.2020.
 */
// TODO: Move to domain package.
data class CustomizableDashboardWidget(
    val order: Int,
    val isEnabled: Boolean,
    val widgetType: DashboardWidgetType
) {

    companion object {

        val default = DashboardWidgetType.default.mapIndexed { index, widgetType ->
            CustomizableDashboardWidget(
                order = index,
                isEnabled = true,
                widgetType = widgetType
            )
        }

    }

}