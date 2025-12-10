package com.nesshop.hobito.features.authentication.domain.usecase

import com.nesshop.hobito.features.authentication.domain.repository.AuthRepository

class ObserveAuthStateUseCase(private val repository: AuthRepository) {

    operator fun invoke() = repository.authState
}
