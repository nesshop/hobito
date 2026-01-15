package com.nesshop.hobito.features.authentication.ui.login.contract

sealed class LoginAction {
    data class SubmitLogin(val email: String, val password: String) : LoginAction()
}