package serg.chuprin.finances.feature.onboarding.presentation.accountssetup.model.store

/**
 * Created by Sergey Chuprin on 10.04.2020.
 */
sealed class AccountsSetupOnboardingIntent {

    object ClickOnPositiveButton : AccountsSetupOnboardingIntent()

    object ClickOnNegativeButton : AccountsSetupOnboardingIntent()

    object ClickOnAcceptBalanceButton : AccountsSetupOnboardingIntent()

    object ClickOnStartUsingAppButton : AccountsSetupOnboardingIntent()

    class InputAmount(
        val enteredAmount: String
    ) : AccountsSetupOnboardingIntent()

}