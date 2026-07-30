package com.nesshop.hobito.features.register_hobby

import com.nesshop.hobito.domain.model.HobbyCategory
import kotlinx.datetime.LocalDate

data class HobbyRegisterState(
    val title: String = "",
    val dateCompleted: LocalDate? = null,
    val rating: Int = 0,
    val category: HobbyCategory = HobbyCategory.MOVIE,
    val notes: String = "",
    val isNotesExpanded: Boolean = false,
    val isLoading: Boolean = false
)

sealed interface HobbyRegisterIntent {
    data class OnTitleChanged(val title: String) : HobbyRegisterIntent
    data class OnDateChanged(val date: LocalDate) : HobbyRegisterIntent
    data class OnRatingChanged(val rating: Int) : HobbyRegisterIntent
    data class OnCategoryChanged(val category: HobbyCategory) : HobbyRegisterIntent
    data class OnNotesChanged(val notes: String) : HobbyRegisterIntent
    data object ToggleNotesExpansion : HobbyRegisterIntent
    data object SaveEntry : HobbyRegisterIntent
    data object Cancel : HobbyRegisterIntent
}

sealed interface HobbyRegisterUiEffect {
    data object NavigateBack : HobbyRegisterUiEffect
    data class ShowError(val message: String) : HobbyRegisterUiEffect
    data object EntrySaved : HobbyRegisterUiEffect
}