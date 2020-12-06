package serg.chuprin.finances.feature.moneyaccount.creation.presentation.model.store

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import serg.chuprin.finances.core.api.di.scopes.ScreenScope
import serg.chuprin.finances.core.api.presentation.currencychoice.model.store.CurrencyChoiceStore
import serg.chuprin.finances.core.api.presentation.currencychoice.model.store.CurrencyChoiceStoreIntentDispatcher
import serg.chuprin.finances.core.mvi.store.BaseStateStore
import javax.inject.Inject

/**
 * Created by Sergey Chuprin on 01.06.2020.
 */
@ScreenScope
class MoneyAccountCreationStore @Inject constructor(
    executor: MoneyAccountCreationActionExecutor,
    bootstrapper: MoneyAccountCreationBootstrapper,
    private val currencyChoiceStore: CurrencyChoiceStore
) : BaseStateStore<MoneyAccountCreationIntent, MoneyAccountCreationEffect, MoneyAccountCreationAction, MoneyAccountCreationState, MoneyAccountCreationEvent>(
    MoneyAccountCreationState(),
    MoneyAccountCreationStateReducer(),
    bootstrapper,
    executor,
    intentToActionMapper = MoneyAccountCreationAction::ExecuteIntent
), CurrencyChoiceStoreIntentDispatcher by currencyChoiceStore {

    override fun start(intentsFlow: Flow<MoneyAccountCreationIntent>, scope: CoroutineScope) {
        currencyChoiceStore.start(emptyFlow(), scope)
        super.start(intentsFlow, scope)
        scope.launch {
            currencyChoiceStore
                .stateFlow
                .collect {
                    ensureActive()
                    dispatchAction(MoneyAccountCreationAction.UpdateCurrencyChoiceState(it))
                }
        }
    }

}