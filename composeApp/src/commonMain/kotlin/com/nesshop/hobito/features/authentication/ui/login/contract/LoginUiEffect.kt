package com.nesshop.hobito.features.authentication.ui.login.contract

sealed interface LoginUiEffect {
    data object NavigateToHome : LoginUiEffect
    data class ShowError(val message: String) : LoginUiEffect

}