package com.nesshop.hobito.data.remote

import com.nesshop.hobito.domain.model.AuthUser
import kotlinx.coroutines.flow.Flow

expect class AuthRemoteDataSource {
    val authState: Flow<AuthUser?>
    suspend fun signInWithEmail(email: String, password: String): Result<AuthUser>
}