package com.nesshop.hobito.core.di

import com.nesshop.hobito.features.authentication.ui.login.LoginViewModel
import com.nesshop.hobito.features.authentication.ui.register.RegisterViewModel
import com.nesshop.hobito.features.main.RootViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::RegisterViewModel)
    viewModelOf(::RootViewModel)
}
