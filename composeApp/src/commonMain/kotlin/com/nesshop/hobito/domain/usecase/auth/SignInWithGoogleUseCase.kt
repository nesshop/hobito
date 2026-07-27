package com.nesshop.hobito.domain.usecase.auth

import com.nesshop.hobito.domain.repository.AuthRepository

class SignInWithGoogleUseCase(private val repository: AuthRepository) {

    suspend operator fun invoke() = repository.signInWithGoogle()
}