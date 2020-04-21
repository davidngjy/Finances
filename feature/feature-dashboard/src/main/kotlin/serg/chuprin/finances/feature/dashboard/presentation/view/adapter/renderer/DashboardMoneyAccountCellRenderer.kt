package serg.chuprin.finances.feature.dashboard.presentation.view.adapter.renderer

import kotlinx.android.synthetic.main.cell_dashboard_money_account.*
import serg.chuprin.adapter.ContainerHolder
import serg.chuprin.adapter.ContainerRenderer
import serg.chuprin.finances.feature.dashboard.R
import serg.chuprin.finances.feature.dashboard.presentation.model.cells.DashboardMoneyAccountCell

/**
 * Created by Sergey Chuprin on 21.04.2020.
 */
class DashboardMoneyAccountCellRenderer : ContainerRenderer<DashboardMoneyAccountCell>() {

    override val type: Int = R.layout.cell_dashboard_money_account

    override fun bindView(holder: ContainerHolder, model: DashboardMoneyAccountCell) {
        with(holder) {
            nameTextView.text = model.name
            balanceTextView.text = model.balance
        }
    }

}