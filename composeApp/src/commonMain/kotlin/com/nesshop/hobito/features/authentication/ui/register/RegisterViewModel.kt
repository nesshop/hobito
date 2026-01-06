package com.nesshop.hobito.features.authentication.ui.register

import com.nesshop.hobito.core.ui.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RegisterViewModel() : BaseViewModel<RegisterState, RegisterEvent, RegisterUiEffect>() {

    private val _uiState = MutableStateFlow(RegisterState())
    override val uiState = _uiState.asStateFlow()

    override fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.OnRegister -> onRegister()
        }
    }

    private fun onRegister() {
        // TODO: Implement register logic
    }
}
