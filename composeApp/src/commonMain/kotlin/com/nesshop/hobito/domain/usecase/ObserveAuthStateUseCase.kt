package com.nesshop.hobito.domain.usecase

import com.nesshop.hobito.AuthRepository

class ObserveAuthStateUseCase(private val repository: AuthRepository) {

    operator fun invoke() = repository.authState
}
