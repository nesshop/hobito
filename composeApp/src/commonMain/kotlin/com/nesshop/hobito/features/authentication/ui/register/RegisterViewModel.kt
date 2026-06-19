package com.nesshop.hobito.features.authentication.ui.register

import com.nesshop.hobito.core.ui.viewmodel.BaseViewModel
import com.nesshop.hobito.domain.usecase.auth.CreateUserWithEmailUseCase
import com.nesshop.hobito.features.authentication.ui.register.contract.RegisterIntent
import com.nesshop.hobito.features.authentication.ui.register.contract.RegisterState
import com.nesshop.hobito.features.authentication.ui.register.contract.RegisterUiEffect

class RegisterViewModel(private val createUserWithEmailUseCase: CreateUserWithEmailUseCase) :
    BaseViewModel<RegisterState, RegisterIntent, RegisterUiEffect>(initialState = RegisterState()) {

    override suspend fun handleIntent(intent: RegisterIntent) {
        when (intent) {
            is RegisterIntent.SubmitRegister -> submitRegistration(intent.email, intent.password)
        }
    }

    private suspend fun submitRegistration(email: String, password: String) {
        setState { copy(isLoading = true) }
        val result = createUserWithEmailUseCase(email, password)
        result.onSuccess {
            sendEffect(RegisterUiEffect.NavigateToHome)
        }.onFailure { throwable ->
            sendEffect(RegisterUiEffect.ShowError(throwable.message ?: "Unknown error"))
        }
        setState { copy(isLoading = false) }
    }
}
