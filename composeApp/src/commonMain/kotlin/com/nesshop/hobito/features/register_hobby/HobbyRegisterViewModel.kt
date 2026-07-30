package com.nesshop.hobito.features.register_hobby

import com.nesshop.hobito.core.ui.viewmodel.BaseViewModel

class HobbyRegisterViewModel : BaseViewModel<HobbyRegisterState, HobbyRegisterIntent, HobbyRegisterUiEffect>(initialState = HobbyRegisterState()) {

    override suspend fun handleIntent(intent: HobbyRegisterIntent) {
        when(intent) {
            is HobbyRegisterIntent.OnTitleChanged -> {
                setState { copy(title = intent.title) }
            }
            is HobbyRegisterIntent.OnDateChanged -> {
                setState { copy(dateCompleted = intent.date) }
            }
            is HobbyRegisterIntent.OnRatingChanged -> {
                setState { copy(rating = intent.rating) }
            }
            is HobbyRegisterIntent.OnCategoryChanged -> {
                setState { copy(category = intent.category) }
            }
            is HobbyRegisterIntent.OnNotesChanged -> {
                setState { copy(notes = intent.notes) }
            }
            HobbyRegisterIntent.ToggleNotesExpansion -> {
                setState { copy(isNotesExpanded = !isNotesExpanded) }
            }
            HobbyRegisterIntent.SaveEntry -> {
                saveEntry()
            }
            HobbyRegisterIntent.Cancel -> {
                sendEffect(HobbyRegisterUiEffect.NavigateBack)
            }
        }
    }

    private fun saveEntry() {
        setState { copy(isLoading = true) }
        // TODO: Implementar guardado con caso de uso a FireStore
        sendEffect(HobbyRegisterUiEffect.EntrySaved)
        sendEffect(HobbyRegisterUiEffect.NavigateBack)
        setState { copy(isLoading = false) }
    }
}
