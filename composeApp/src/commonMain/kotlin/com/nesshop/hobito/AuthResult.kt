package com.nesshop.hobito

import com.nesshop.hobito.domain.model.AuthUser

interface AuthResult {

    data class Success(val user: AuthUser) : AuthResult
    data class Error(val message: String = "", val cause: Throwable? = null) : AuthResult
}