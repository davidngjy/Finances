package serg.chuprin.finances.core.api.presentation.currencychoice.model.store

import serg.chuprin.finances.core.api.presentation.currencychoice.model.cells.CurrencyCell

/**
 * Created by Sergey Chuprin on 06.04.2020.
 */
sealed class CurrencyChoiceIntent {

    /**
     * User wants to close currency picker.
     */
    object ClickOnCloseCurrencyPicker : CurrencyChoiceIntent()

    /**
     * User wants to open currency picker.
     */
    object ClickOnCurrencyPicker : CurrencyChoiceIntent()

    /**
     * Some search query was entered.
     */
    class EnterCurrencySearchQuery(
        val searchQuery: String
    ) : CurrencyChoiceIntent()

    /**
     * User has chosen a particular currency from currencies list.
     */
    class ClickOnCurrencyCell(
        val currencyCell: CurrencyCell
    ) : CurrencyChoiceIntent()

}