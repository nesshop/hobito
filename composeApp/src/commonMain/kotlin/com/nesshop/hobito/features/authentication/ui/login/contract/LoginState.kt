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