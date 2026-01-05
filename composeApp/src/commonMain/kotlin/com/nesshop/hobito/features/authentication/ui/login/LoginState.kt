package com.nesshop.hobito.features.authentication.ui.login

data class LoginState(
    val isLoading: Boolean = false,
    val isLoginSuccessful: Boolean = false,
    val error: String? = null
)