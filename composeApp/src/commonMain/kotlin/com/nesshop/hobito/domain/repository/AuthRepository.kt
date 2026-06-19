package com.nesshop.hobito.domain.repository

import com.nesshop.hobito.domain.model.AuthUser
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val authState: Flow<AuthUser?>
    suspend fun signInWithEmail(email: String, password: String): Result<AuthUser>
    suspend fun createUserWithEmail(email: String, password: String) :Result<AuthUser>
}