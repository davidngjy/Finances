package serg.chuprin.finances.feature.moneyaccount.creation.presentation.model.store

/**
 * Created by Sergey Chuprin on 01.06.2020.
 */
sealed class MoneyAccountCreationIntent {

    object BackPress : MoneyAccountCreationIntent()

    object ClickOnSaveButton : MoneyAccountCreationIntent()

    class EnterAccountName(
        val accountName: String
    ) : MoneyAccountCreationIntent()

    class EnterBalance(
        val balance: String
    ) : MoneyAccountCreationIntent()

}