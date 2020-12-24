package serg.chuprin.finances.core.categories.shares.presentation.view.adapter.diff

import serg.chuprin.finances.core.api.presentation.model.cells.BaseCell
import serg.chuprin.finances.core.api.presentation.view.adapter.diff.DiffCallback
import serg.chuprin.finances.core.categories.shares.presentation.model.cell.CategoryChipCell

/**
 * Created by Sergey Chuprin on 28.04.2020.
 */
class CategoryChipCellsAdapterDiffCallback : DiffCallback<BaseCell>() {

    override fun getChangePayload(oldCell: BaseCell, newCell: BaseCell): Any? {
        if (oldCell is CategoryChipCell && newCell is CategoryChipCell) {
            return CategoryChipCell.CellChangedPayload()
        }
        return super.getChangePayload(oldCell, newCell)
    }

}