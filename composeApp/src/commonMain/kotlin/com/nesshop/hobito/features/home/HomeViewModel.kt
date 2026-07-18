package com.nesshop.hobito.features.home

import com.nesshop.hobito.core.ui.viewmodel.BaseViewModel

class HomeViewModel: BaseViewModel<HomeState, HomeIntent, HomeUiEffect>(initialState = HomeState()) {
    override suspend fun handleIntent(intent: HomeIntent) {
        when(intent) {
            HomeIntent.LoadHomeData -> {
                // TODO: Llamada a FireStore y/o SQLDelight para obtener los datos
            }

            HomeIntent.OnViewAllClicked -> {
                sendEffect(HomeUiEffect.NavigateToAllActivities)
            }
            is HomeIntent.OnActivityClicked -> {
                sendEffect(HomeUiEffect.NavigateToActivityDetails(intent.item))
            }
        }
    }
}