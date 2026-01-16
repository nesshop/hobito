package com.nesshop.hobito.features.authentication.ui.register

import com.nesshop.hobito.core.ui.viewmodel.BaseViewModel
import com.nesshop.hobito.features.authentication.ui.register.contract.RegisterEvent
import com.nesshop.hobito.features.authentication.ui.register.contract.RegisterState
import com.nesshop.hobito.features.authentication.ui.register.contract.RegisterUiEffect

class RegisterViewModel() : BaseViewModel<RegisterState, RegisterEvent, RegisterUiEffect>(initialState = RegisterState()) {

    override suspend fun handleEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.OnRegister -> onRegister()
        }
    }

    private fun onRegister() {
        // TODO: Implement register logic
    }
}
