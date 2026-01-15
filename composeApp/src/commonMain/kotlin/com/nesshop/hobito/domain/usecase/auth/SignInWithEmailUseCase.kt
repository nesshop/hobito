package com.nesshop.hobito.domain.usecase.auth

import com.nesshop.hobito.domain.repository.AuthRepository

class SignInWithEmailUseCase(private val repository: AuthRepository) {

    suspend operator fun invoke(user: String, password: String) =
        repository.signInWithEmail(user, password)

}