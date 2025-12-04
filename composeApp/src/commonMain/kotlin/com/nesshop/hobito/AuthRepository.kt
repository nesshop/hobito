package com.nesshop.hobito

import com.nesshop.hobito.domain.model.AuthUser
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val authState: Flow<AuthUser?>
    suspend fun signInWithEmail(email:String, password: String): AuthResult
}