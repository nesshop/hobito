package com.nesshop.hobito.core.di

import com.nesshop.hobito.features.authentication.ui.login.LoginViewModel
import com.nesshop.hobito.features.authentication.ui.register.RegisterViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::RegisterViewModel)
}
