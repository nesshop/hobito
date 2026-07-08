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
}