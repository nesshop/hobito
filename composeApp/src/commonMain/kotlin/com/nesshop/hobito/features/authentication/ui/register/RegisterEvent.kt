package com.nesshop.hobito.features.authentication.ui.register

sealed class RegisterEvent {
    data object OnRegister : RegisterEvent()
}
