package com.nesshop.hobito.root.contract

sealed interface RootState {
    data object Loading : RootState
    data object Authenticated : RootState
    data object Unauthenticated : RootState
}

sealed interface RootIntent
sealed interface RootUiEffect
