package com.nesshop.hobito.features.home

import com.nesshop.hobito.domain.model.HomeItem

data class HomeState(
    val userName: String = "Ernesto",
    val lastCompleted: HomeItem? = HomeItem(
        title = "title",
        subtitle = "subtitle",
        category = "MOVIE",
        date = "date",
        imageRes = null
    ),
    val recentActivities: List<HomeItem> = listOf(
        HomeItem(
            title = "title",
            subtitle = "subtitle",
            category = "MOVIE",
            date = "date",
            rating = 4.5,
            imageRes = null
        ),
        HomeItem(
            title = "title",
            subtitle = "subtitle",
            category = "GAME",
            date = "date",
            rating = 4.5,
            imageRes = null
        ),
        HomeItem(
            title = "title",
            subtitle = "subtitle",
            category = "SERIES",
            date = "date",
            rating = 4.5,
            imageRes = null
        ),
        HomeItem(
            title = "title",
            subtitle = "subtitle",
            category = "MOVIE",
            date = "date",
            rating = 4.5,
            imageRes = null
        )
    ),
    val isLoading: Boolean = false
)

sealed interface HomeIntent {
    data object LoadHomeData : HomeIntent
    data object OnViewAllClicked : HomeIntent
    data class OnActivityClicked(val item: HomeItem) : HomeIntent
}

sealed interface HomeUiEffect {
    data object NavigateToAllActivities : HomeUiEffect
    data class NavigateToActivityDetails(val item: HomeItem) : HomeUiEffect
}