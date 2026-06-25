package com.nesshop.hobito.features.authentication.ui.login

import com.nesshop.hobito.Res
import com.nesshop.hobito.core.ui.viewmodel.BaseViewModel
import com.nesshop.hobito.domain.usecase.auth.SignInWithEmailUseCase
import com.nesshop.hobito.domain.validation.EmailValidator
import com.nesshop.hobito.domain.validation.PasswordValidation
import com.nesshop.hobito.domain.validation.PasswordValidator
import com.nesshop.hobito.features.authentication.ui.login.contract.LoginIntent
import com.nesshop.hobito.features.authentication.ui.login.contract.LoginState
import com.nesshop.hobito.features.authentication.ui.login.contract.LoginUiEffect
import com.nesshop.hobito.register_screen_email_error
import com.nesshop.hobito.register_screen_password_error

class LoginViewModel(
    private val signInWithEmailUseCase: SignInWithEmailUseCase,
) : BaseViewModel<LoginState, LoginIntent, LoginUiEffect>(initialState = LoginState()) {

    override suspend fun handleIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.SubmitLogin -> {
                signInWithEmail(intent.email, intent.password)
            }

            is LoginIntent.ValidateEmail -> {
                validateEmail(intent.email)
                updateFormValidState()
            }

            is LoginIntent.ValidatePassword -> {
                validatePassword(intent.password)
                updateFormValidState()
            }

            is LoginIntent.OnEmailChanged -> {
                setState { copy(email = intent.email) }
                if (uiState.value.emailError != null) validateEmail(intent.email)
                updateFormValidState()
            }

            is LoginIntent.OnPasswordChanged -> {
                setState { copy(password = intent.password) }
                if (uiState.value.passwordError != null) validatePassword(intent.password)
                updateFormValidState()
            }
            LoginIntent.TogglePasswordVisibility -> {
                setState { copy(isPasswordVisible = !isPasswordVisible) }
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

    private fun validateEmail(email: String) {
        val isValid = EmailValidator.validate(email)
        setState {
            copy(
                emailError = if (isValid || email.isEmpty()) null else Res.string.register_screen_email_error
            )
        }
    }

    private fun validatePassword(password: String) {
        val validation = PasswordValidator.validate(password)
        setState {
            copy(
                passwordError = if (validation is PasswordValidation.Valid || password.isEmpty()) null else Res.string.register_screen_password_error
            )
        }
    }

    private fun updateFormValidState() {
        val state = uiState.value
        val isEmailValid = EmailValidator.validate(state.email)
        val isPasswordValid = PasswordValidator.validate(state.password) is PasswordValidation.Valid
        setState {
            copy(isFormValid = isEmailValid && isPasswordValid)
        }
    }
}