package com.nesshop.hobito.core.navigation

import kotlinx.serialization.Serializable

sealed class Route {

    @Serializable
    data object Login : Route()
    @Serializable
    data object Register : Route()

}