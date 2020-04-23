package serg.chuprin.finances.feature.dashboard.presentation.view.adapter.moneyaccounts.renderer

import kotlinx.android.synthetic.main.cell_dashboard_money_account.*
import serg.chuprin.adapter.ContainerHolder
import serg.chuprin.adapter.ContainerRenderer
import serg.chuprin.finances.core.api.presentation.view.extensions.makeVisibleOrGone
import serg.chuprin.finances.feature.dashboard.R
import serg.chuprin.finances.feature.dashboard.presentation.model.cells.moneyaccounts.DashboardMoneyAccountCell
import serg.chuprin.finances.feature.dashboard.presentation.view.adapter.moneyaccounts.diff.payload.DashboardMoneyAccountCellChangedPayload

/**
 * Created by Sergey Chuprin on 21.04.2020.
 */
class DashboardMoneyAccountCellRenderer : ContainerRenderer<DashboardMoneyAccountCell>() {

    override val type: Int = R.layout.cell_dashboard_money_account

    override fun bindView(holder: ContainerHolder, model: DashboardMoneyAccountCell) {
        bindViews(holder, model)
    }

    override fun bindView(
        holder: ContainerHolder,
        model: DashboardMoneyAccountCell,
        payloads: MutableList<Any>
    ) {
        if (DashboardMoneyAccountCellChangedPayload in payloads) {
            bindViews(holder, model)
        }
    }

    private fun bindViews(
        holder: ContainerHolder,
        model: DashboardMoneyAccountCell
    ) {
        with(holder) {
            nameTextView.text = model.name
            balanceTextView.text = model.balance
            cardView.isActivated = model.favoriteIconIsVisible
            favoriteImageView.makeVisibleOrGone(model.favoriteIconIsVisible)
        }
    }

}