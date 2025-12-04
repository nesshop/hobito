package com.nesshop.hobito.di

import com.nesshop.hobito.AuthRepository
import com.nesshop.hobito.data.AuthRepositoryIos
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<AuthRepository> { AuthRepositoryIos() }
}