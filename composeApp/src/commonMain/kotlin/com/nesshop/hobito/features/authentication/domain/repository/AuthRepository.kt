package com.nesshop.hobito.features.authentication.domain.repository

import com.nesshop.hobito.AuthResult
import com.nesshop.hobito.features.authentication.domain.model.AuthUser
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val authState: Flow<AuthUser?>
    suspend fun signInWithEmail(email:String, password: String): AuthResult
}