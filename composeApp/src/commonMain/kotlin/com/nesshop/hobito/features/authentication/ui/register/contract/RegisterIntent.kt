package com.nesshop.hobito.features.authentication.ui.register.contract

sealed interface RegisterIntent {
    data class SubmitRegister(val email: String, val password: String) : RegisterIntent
    data class ValidateEmail(val email: String) : RegisterIntent
}