package serg.chuprin.finances.core.impl.presentation.model.formatter

import serg.chuprin.finances.core.api.extensions.EMPTY_STRING
import serg.chuprin.finances.core.api.presentation.model.formatter.AmountFormatter
import java.math.BigDecimal
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.*
import javax.inject.Inject

/**
 * Created by Sergey Chuprin on 12.04.2020.
 */
internal class AmountFormatterImpl @Inject constructor() : AmountFormatter {

    private val groupSeparator: Char
    private val decimalSeparator: Char
    private val numberFormat: NumberFormat
    private val decimalFormat: DecimalFormatSymbols

    init {
        val locale = Locale.getDefault()
        numberFormat = NumberFormat.getInstance(locale)
        decimalFormat = DecimalFormatSymbols.getInstance(locale)
        groupSeparator = decimalFormat.groupingSeparator
        decimalSeparator = decimalFormat.decimalSeparator
    }

    override fun formatInput(input: String, currency: Currency): String {
        setupFormatter(currency)

        if (input.isEmpty()) {
            return "0"
        }

        // User can type both ',' and '.' symbols.
        // Do not accept symbol if it is not decimal separator.
        if (!input.last().isDigit() && input.last() != decimalSeparator) {
            return input.dropLast(1)
        }

        // Do not allow multiple decimal separators.
        if (input.count { char -> char == decimalSeparator } > 1) {
            return input.dropLast(1)
        }

        // If last char is separator, parsing is unavailable.
        val normalizedStr = input.replace(("\\$groupSeparator").toRegex(), EMPTY_STRING)
        if (normalizedStr.isEmpty() || normalizedStr.last() == decimalSeparator) {
            return input
        }

        val decimalSeparatorIndex = normalizedStr.indexOf(decimalSeparator)
        if (normalizedStr.last() == '0'
            && decimalSeparatorIndex != -1
            && (normalizedStr.length - (decimalSeparatorIndex + 1))
            < numberFormat.maximumFractionDigits
        ) {
            return input
        }
        return try {
            val bigDecimal = if (normalizedStr.contains(decimalSeparator, true)) {
                BigDecimal(normalizedStr.replace(",", "."))
            } else {
                BigDecimal(normalizedStr)
            }
            numberFormat.format(bigDecimal)
        } catch (e: NumberFormatException) {
            input
        }
    }

    private fun setupFormatter(currency: Currency) {
        numberFormat.currency = currency
    }

}