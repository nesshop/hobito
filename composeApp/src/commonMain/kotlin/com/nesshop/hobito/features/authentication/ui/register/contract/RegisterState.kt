package com.nesshop.hobito.features.authentication.ui.register.contract

import org.jetbrains.compose.resources.StringResource

data class RegisterState(
    val isLoading: Boolean = false,
    val emailError: StringResource? = null
)