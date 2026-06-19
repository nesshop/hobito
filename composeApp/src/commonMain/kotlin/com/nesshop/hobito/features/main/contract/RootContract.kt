package com.nesshop.hobito.features.main.contract

sealed interface RootState {
    data object Loading : RootState
    data object Authenticated : RootState
    data object Unauthenticated : RootState
}

sealed interface RootEvent
sealed interface RootUiEffect
