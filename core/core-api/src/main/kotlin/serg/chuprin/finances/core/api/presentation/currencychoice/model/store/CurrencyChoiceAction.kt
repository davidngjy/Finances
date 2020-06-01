package serg.chuprin.finances.core.api.presentation.currencychoice.model.store

import java.util.*

/**
 * Created by Sergey Chuprin on 06.04.2020.
 */
sealed class CurrencyChoiceAction {

    class SetCurrenciesParams(
        val currentCurrency: Currency,
        val availableCurrencies: List<Currency>
    ) : CurrencyChoiceAction()

    class ExecuteIntent(
        val intent: CurrencyChoiceIntent
    ) : CurrencyChoiceAction()

}