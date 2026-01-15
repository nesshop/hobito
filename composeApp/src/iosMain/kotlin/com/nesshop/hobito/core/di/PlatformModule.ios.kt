package com.nesshop.hobito.core.di

import com.nesshop.hobito.data.remote.AuthRemoteDataSource
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<AuthRemoteDataSource> { AuthRemoteDataSource() }
}