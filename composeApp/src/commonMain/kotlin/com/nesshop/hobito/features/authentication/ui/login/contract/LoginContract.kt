package com.nesshop.hobito.features.authentication.ui.login.contract

import org.jetbrains.compose.resources.StringResource

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val emailError: StringResource? = null,
    val passwordError: StringResource? = null,
    val isPasswordVisible: Boolean = false,
    val isFormValid: Boolean = false
)

sealed interface LoginIntent {
    data class SubmitLogin(val email: String, val password: String) : LoginIntent
    data class ValidateEmail(val email: String) : LoginIntent
    data class ValidatePassword(val password: String) : LoginIntent
    data class OnEmailChanged(val email: String) : LoginIntent
    data class OnPasswordChanged(val password: String) : LoginIntent
    data object TogglePasswordVisibility : LoginIntent
}

sealed interface LoginUiEffect {
    data object NavigateToHome : LoginUiEffect
    data class ShowError(val message: String) : LoginUiEffect
}
