package com.nesshop.hobito.features.authentication.ui.login

import com.nesshop.hobito.core.ui.viewmodel.BaseViewModel
import com.nesshop.hobito.domain.usecase.auth.SignInWithEmailUseCase
import com.nesshop.hobito.features.authentication.ui.login.contract.LoginIntent
import com.nesshop.hobito.features.authentication.ui.login.contract.LoginState
import com.nesshop.hobito.features.authentication.ui.login.contract.LoginUiEffect

class LoginViewModel(
    private val signInWithEmailUseCase: SignInWithEmailUseCase,
) : BaseViewModel<LoginState, LoginIntent, LoginUiEffect>(initialState = LoginState()) {

    override suspend fun handleIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.SubmitLogin -> {
                signInWithEmail(intent.email, intent.password)
            }
        }
    }

    private suspend fun signInWithEmail(email: String, password: String) {
        setState { copy(isLoading = true) }

        val result = signInWithEmailUseCase(email, password)

        result.onSuccess {
            sendEffect(LoginUiEffect.NavigateToHome)
        }.onFailure { throwable ->
            sendEffect(LoginUiEffect.ShowError(throwable.message ?: "Unknown error"))
        }
        setState { copy(isLoading = false) }
    }
}