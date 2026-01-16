package com.nesshop.hobito.features.authentication.ui.login

import androidx.lifecycle.viewModelScope
import com.nesshop.hobito.core.ui.viewmodel.BaseViewModel
import com.nesshop.hobito.domain.usecase.auth.SignInWithEmailUseCase
import com.nesshop.hobito.features.authentication.ui.login.contract.LoginAction
import com.nesshop.hobito.features.authentication.ui.login.contract.LoginState
import com.nesshop.hobito.features.authentication.ui.login.contract.LoginUiEffect
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val signInWithEmailUseCase: SignInWithEmailUseCase,
) : BaseViewModel<LoginState, LoginAction, LoginUiEffect>(initialState = LoginState()) {

    override suspend fun handleEvent(event: LoginAction) {
        when (event) {
            is LoginAction.SubmitLogin -> {
                signInWithEmail(event.email, event.password)
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