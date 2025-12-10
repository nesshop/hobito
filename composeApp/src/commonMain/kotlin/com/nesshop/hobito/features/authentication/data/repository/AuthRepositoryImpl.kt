package com.nesshop.hobito.features.authentication.data.repository

import com.nesshop.hobito.features.authentication.data.remote.AuthRemoteDataSource
import com.nesshop.hobito.features.authentication.domain.model.AuthUser
import com.nesshop.hobito.features.authentication.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl(private val remoteDataSource: AuthRemoteDataSource) : AuthRepository {
    override val authState: Flow<AuthUser?> = remoteDataSource.authState

    override suspend fun signInWithEmail(
        email: String,
        password: String
    ): Result<AuthUser> {
        return remoteDataSource.signInWithEmail(email, password)
    }
}