package com.nesshop.hobito

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.nesshop.hobito.core.di.authModule
import com.nesshop.hobito.core.di.platformModule
import com.nesshop.hobito.core.di.viewModelModule
import com.nesshop.hobito.core.navigation.NavigationWrapper
import com.nesshop.hobito.designsystem.theme.HobitoTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication

@Composable
@Preview
fun App() {
    KoinApplication(application = {
        modules(
            platformModule, authModule, viewModelModule
        )
    }) {
        HobitoTheme {
            val navController = rememberNavController()
            NavigationWrapper(navController)
        }
    }
}