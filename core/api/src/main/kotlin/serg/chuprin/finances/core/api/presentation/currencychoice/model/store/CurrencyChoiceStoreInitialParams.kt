package serg.chuprin.finances.core.api.presentation.currencychoice.model.store

import java.util.*

/**
 * Created by Sergey Chuprin on 01.06.2020.
 */
class CurrencyChoiceStoreInitialParams(
    val chosenCurrency: Currency,
    val availableCurrencies: List<Currency>
)