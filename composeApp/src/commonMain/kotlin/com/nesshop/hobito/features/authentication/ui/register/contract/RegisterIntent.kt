package com.nesshop.hobito.features.authentication.ui.register.contract

sealed class RegisterIntent {
    data class SubmitRegister(val email: String, val password: String) : RegisterIntent()
}