package com.nesshop.hobito.features.authentication.ui.login

sealed class LoginEvent {
    data class OnLogin(val email: String, val password: String) : LoginEvent()
}