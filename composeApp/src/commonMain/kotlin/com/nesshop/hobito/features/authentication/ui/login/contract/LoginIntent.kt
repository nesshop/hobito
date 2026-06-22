package com.nesshop.hobito.features.authentication.ui.login.contract

sealed interface LoginIntent {
    data class SubmitLogin(val email: String, val password: String) : LoginIntent
}