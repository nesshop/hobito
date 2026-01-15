package com.nesshop.hobito.features.authentication.ui.register.contract

sealed class RegisterEvent {
    data object OnRegister : RegisterEvent()
}