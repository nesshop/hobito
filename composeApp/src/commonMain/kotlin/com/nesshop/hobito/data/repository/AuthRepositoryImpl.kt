package com.nesshop.hobito.data.repository

import com.nesshop.hobito.data.remote.AuthRemoteDataSource
import com.nesshop.hobito.domain.model.AuthUser
import com.nesshop.hobito.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl(private val remoteDataSource: AuthRemoteDataSource) : AuthRepository {
    override val authState: Flow<AuthUser?> = remoteDataSource.authState

    override suspend fun signInWithEmail(
        email: String,
        password: String
    ): Result<AuthUser> {
        return remoteDataSource.signInWithEmail(email, password)
    }

    override suspend fun createUserWithEmail(
        email: String,
        password: String
    ): Result<AuthUser> {
        return remoteDataSource.createUserWithEmail(email, password)
    }
}