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