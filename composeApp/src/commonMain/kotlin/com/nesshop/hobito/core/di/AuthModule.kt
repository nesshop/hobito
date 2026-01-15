package com.nesshop.hobito.core.di

import com.nesshop.hobito.data.repository.AuthRepositoryImpl
import com.nesshop.hobito.domain.repository.AuthRepository
import com.nesshop.hobito.domain.usecase.auth.ObserveAuthStateUseCase
import com.nesshop.hobito.domain.usecase.auth.SignInWithEmailUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val authModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get()) }

    factory { ObserveAuthStateUseCase(get()) }
    factoryOf(::SignInWithEmailUseCase)
}