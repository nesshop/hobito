package com.nesshop.hobito.domain.usecase.auth

import com.nesshop.hobito.domain.repository.AuthRepository

class ObserveAuthStateUseCase(private val repository: AuthRepository) {

    operator fun invoke() = repository.authState
}