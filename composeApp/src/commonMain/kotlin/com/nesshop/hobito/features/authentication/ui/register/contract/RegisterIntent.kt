package com.nesshop.hobito.features.authentication.ui.register.contract

sealed interface RegisterIntent {
    data class SubmitRegister(val email: String, val password: String) : RegisterIntent
    data class ValidateEmail(val email: String) : RegisterIntent
    data class ValidatePassword(val password: String) : RegisterIntent
    data class ValidateRepeatPassword(val password: String, val repeatPassword: String) :
        RegisterIntent

    data class OnEmailChanged(val email: String) : RegisterIntent
    data class OnPasswordChanged(val password: String) : RegisterIntent
    data class OnRepeatPasswordChanged(val password: String, val repeatPassword: String) :
        RegisterIntent
    data object TogglePasswordVisibility : RegisterIntent
    data object ToggleRepeatPasswordVisibility : RegisterIntent
}