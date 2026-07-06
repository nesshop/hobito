package com.nesshop.hobito.features.authentication.ui.register.contract

import org.jetbrains.compose.resources.StringResource

data class RegisterState(
    val email: String = "",
    val password: String = "",
    val repeatPassword: String = "",
    val isLoading: Boolean = false,
    val emailError: StringResource? = null,
    val passwordError: StringResource? = null,
    val repeatPasswordError: StringResource? = null,
    val isPasswordVisible: Boolean = false,
    val isRepeatPasswordVisible: Boolean = false,
    val isFormValid: Boolean = false
)

sealed interface RegisterIntent {
    data class SubmitRegister(val email: String, val password: String) : RegisterIntent
    data class ValidateEmail(val email: String) : RegisterIntent
    data class ValidatePassword(val password: String) : RegisterIntent
    data class ValidateRepeatPassword(val password: String, val repeatPassword: String) : RegisterIntent
    data class OnEmailChanged(val email: String) : RegisterIntent
    data class OnPasswordChanged(val password: String) : RegisterIntent
    data class OnRepeatPasswordChanged(val password: String, val repeatPassword: String) : RegisterIntent
    data object TogglePasswordVisibility : RegisterIntent
    data object ToggleRepeatPasswordVisibility : RegisterIntent
}

sealed interface RegisterUiEffect {
    data class ShowError(val message: String) : RegisterUiEffect
    data object NavigateToHome : RegisterUiEffect
}
