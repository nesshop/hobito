package com.nesshop.hobito.navigation

import kotlinx.serialization.Serializable

sealed class Route {

    @Serializable
    data object Login : Route()

    @Serializable
    data object Register : Route()

    @Serializable
    data object Home : Route()

}