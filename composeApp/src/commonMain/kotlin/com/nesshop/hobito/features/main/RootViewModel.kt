package com.nesshop.hobito.features.main

import androidx.lifecycle.viewModelScope
import com.nesshop.hobito.core.ui.viewmodel.BaseViewModel
import com.nesshop.hobito.domain.usecase.auth.ObserveAuthStateUseCase
import com.nesshop.hobito.features.main.contract.RootIntent
import com.nesshop.hobito.features.main.contract.RootState
import com.nesshop.hobito.features.main.contract.RootUiEffect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class RootViewModel(
    private val observeAuthStateUseCase: ObserveAuthStateUseCase
) : BaseViewModel<RootState, RootIntent, RootUiEffect>(initialState = RootState.Loading) {

    init {
        observeAuthState()
    }

    private fun observeAuthState() {
        observeAuthStateUseCase()
            .onEach { user ->
                if (user != null) {
                    setState { RootState.Authenticated }
                } else {
                    setState { RootState.Unauthenticated }
                }
            }
            .launchIn(viewModelScope)
    }

    override suspend fun handleIntent(intent: RootIntent) {}
}
