package com.nesshop.hobito.features.authentication.domain.usecase

import com.nesshop.hobito.features.authentication.domain.repository.AuthRepository

class SignInWithEmailUseCase(private val repository: AuthRepository) {

    suspend operator fun invoke(user: String, password: String) =
        repository.signInWithEmail(user, password)

}