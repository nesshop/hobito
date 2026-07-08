package com.nesshop.hobito.root

import androidx.compose.runtime.Composable
import com.nesshop.hobito.dashboard.DashboardScreen
import com.nesshop.hobito.features.authentication.ui.AuthNavHost
import com.nesshop.hobito.root.contract.RootState

@Composable
fun RootScreen(state: RootState){
    when(state) {
        RootState.Loading -> {
        // TODO: Usar Splash o loading
        }
        RootState.Authenticated -> {
            DashboardScreen()
        }
        RootState.Unauthenticated -> {
            AuthNavHost()
        }
    }
}