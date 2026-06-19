package com.nesshop.hobito.domain.usecase.auth

import com.nesshop.hobito.domain.repository.AuthRepository

class CreateUserWithEmailUseCase(private val repository: AuthRepository) {

    suspend operator fun invoke(email: String, password: String) =
        repository.createUserWithEmail(email, password);

}