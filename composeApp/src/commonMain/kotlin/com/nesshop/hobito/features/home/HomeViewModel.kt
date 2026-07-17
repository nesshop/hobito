package com.nesshop.hobito.features.home

import com.nesshop.hobito.core.ui.viewmodel.BaseViewModel

class HomeViewModel: BaseViewModel<HomeState, HomeIntent, HomeUiEffect>(initialState = HomeState()) {
    override suspend fun handleIntent(intent: HomeIntent) {
        TODO("Not yet implemented")
    }
}