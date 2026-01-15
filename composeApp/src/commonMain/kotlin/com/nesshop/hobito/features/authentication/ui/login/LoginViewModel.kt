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
) : BaseViewModel<LoginState, LoginAction, LoginUiEffect>() {

    private val _uiState = MutableStateFlow(LoginState())
    override val uiState = _uiState.asStateFlow()

    override fun onEvent(event: LoginAction) {
        when (event) {
            is LoginAction.SubmitLogin -> {
                signInWithEmail(event.email, event.password)
            }
        }
    }

    private fun signInWithEmail(email: String, password: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val result = signInWithEmailUseCase(email, password)
            result.onSuccess {
                _effects.send(LoginUiEffect.NavigateToHome)
            }.onFailure { throwable ->
                _effects.send(LoginUiEffect.ShowError(throwable.message ?: "Unknown error"))
            }
            _uiState.update {
                it.copy(
                    isLoading = false
                )
            }
        }
    }
}