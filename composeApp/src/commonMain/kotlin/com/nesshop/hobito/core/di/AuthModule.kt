package com.nesshop.hobito.core.di

import com.nesshop.hobito.features.authentication.data.repository.AuthRepositoryImpl
import com.nesshop.hobito.features.authentication.domain.repository.AuthRepository
import com.nesshop.hobito.features.authentication.domain.usecase.ObserveAuthStateUseCase
import com.nesshop.hobito.features.authentication.domain.usecase.SignInWithEmailUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val authModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get()) }

    factory { ObserveAuthStateUseCase(get()) }
    factoryOf(::SignInWithEmailUseCase)
}