package com.nesshop.hobito.features.authentication.ui.login.contract

sealed interface LoginIntent {
    data class SubmitLogin(val email: String, val password: String) : LoginIntent
    data class ValidateEmail(val email: String) : LoginIntent
    data class ValidatePassword(val password: String) : LoginIntent
    data class OnEmailChanged(val email: String) : LoginIntent
    data class OnPasswordChanged(val password: String) : LoginIntent
    data object TogglePasswordVisibility : LoginIntent
}
