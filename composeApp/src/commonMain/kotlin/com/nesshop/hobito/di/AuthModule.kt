package com.nesshop.hobito.di

import com.nesshop.hobito.domain.usecase.ObserveAuthStateUseCase
import com.nesshop.hobito.domain.usecase.SignInWithEmailUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val authModule = module {
    factory { ObserveAuthStateUseCase(get()) }
    factoryOf(::SignInWithEmailUseCase)
}