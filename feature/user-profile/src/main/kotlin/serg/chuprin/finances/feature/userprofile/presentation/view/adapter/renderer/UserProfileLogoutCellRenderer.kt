package serg.chuprin.finances.feature.userprofile.presentation.view.adapter.renderer

import kotlinx.android.synthetic.main.cell_user_profile_logout.*
import serg.chuprin.adapter.Click
import serg.chuprin.adapter.ContainerHolder
import serg.chuprin.finances.core.api.presentation.view.adapter.renderer.ContainerRenderer
import serg.chuprin.adapter.LongClick
import serg.chuprin.finances.core.api.presentation.view.extensions.onViewClick
import serg.chuprin.finances.feature.userprofile.R
import serg.chuprin.finances.feature.userprofile.presentation.model.cells.UserProfileLogoutCell

/**
 * Created by Sergey Chuprin on 31.07.2020.
 */
class UserProfileLogoutCellRenderer : ContainerRenderer<UserProfileLogoutCell>() {

    override val type: Int = R.layout.cell_user_profile_logout

    override fun onVhCreated(
        viewHolder: ContainerHolder,
        clickListener: Click?,
        longClickListener: LongClick?
    ) {
        with(viewHolder) {
            logoutButton.onViewClick { view ->
                clickListener?.onClick(view, adapterPosition)
            }
        }
    }

}