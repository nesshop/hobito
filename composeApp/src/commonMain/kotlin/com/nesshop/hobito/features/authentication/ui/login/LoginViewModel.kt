package com.nesshop.hobito.features.authentication.ui.login

import androidx.lifecycle.viewModelScope
import com.nesshop.hobito.core.ui.viewmodel.BaseViewModel
import com.nesshop.hobito.features.authentication.domain.usecase.SignInWithEmailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val signInWithEmailUseCase: SignInWithEmailUseCase
) : BaseViewModel<LoginState, LoginEvent>() {

    private val _uiState = MutableStateFlow(LoginState())
    override val uiState = _uiState.asStateFlow()

    override fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnLogin -> {
                signInWithEmail(event.email, event.password)
            }
        }
    }

    private fun signInWithEmail(email: String, password: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val result = signInWithEmailUseCase(email, password)
            result.onSuccess {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        isLoginSuccessful = true
                    )
                }
            }.onFailure { throwable ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = throwable.message
                    )
                }
            }
        }
    }
}