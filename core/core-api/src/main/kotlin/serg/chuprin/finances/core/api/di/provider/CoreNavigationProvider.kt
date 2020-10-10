package serg.chuprin.finances.core.api.di.provider

import serg.chuprin.finances.core.api.presentation.navigation.*

/**
 * Created by Sergey Chuprin on 03.04.2020.
 */
interface CoreNavigationProvider {

    val dashboardNavigation: DashboardNavigation

    val onboardingNavigation: OnboardingNavigation

    val userProfileNavigation: UserProfileNavigation

    val authorizationNavigation: AuthorizationNavigation

    val moneyAccountsListNavigation: MoneyAccountsListNavigation

}