package com.nesshop.hobito.domain.usecase

import com.nesshop.hobito.AuthRepository

class SignInWithEmailUseCase(private val repository: AuthRepository) {

    suspend operator fun invoke(user: String, password: String) =
        repository.signInWithEmail(user, password)

}