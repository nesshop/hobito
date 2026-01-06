package com.nesshop.hobito.features.authentication.ui.register

sealed interface RegisterUiEffect {
    data class ShowError(val message: String) : RegisterUiEffect

}