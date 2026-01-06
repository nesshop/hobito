package com.nesshop.hobito.core.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseViewModel<S, E, F> : ViewModel() {
    abstract val uiState: StateFlow<S>
    protected val _effects = Channel<F>(Channel.BUFFERED)
    val effects: Flow<F> get() = _effects.receiveAsFlow()
    abstract fun onEvent(event: E)
}
