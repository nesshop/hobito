package com.nesshop.hobito.core.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<S, E> : ViewModel() {
    abstract val uiState: StateFlow<S>
    abstract fun onEvent(event: E)
}
