package com.nesshop.hobito.navigation

import kotlinx.serialization.Serializable

sealed class AuthRoute {

    @Serializable
    data object Login : AuthRoute()

    @Serializable
    data object Register : AuthRoute()

}

sealed class DashboardRoute {
    @Serializable
    data object Home : DashboardRoute()
    @Serializable
    data object AllActivities : DashboardRoute()
    @Serializable
    data object Search : DashboardRoute()
    @Serializable
    data object Statistics : DashboardRoute()
    @Serializable
    data object Profile : DashboardRoute()
}