package com.nesshop.hobito

import androidx.compose.runtime.Composable
import com.nesshop.hobito.designsystem.theme.HobitoTheme
import com.nesshop.hobito.di.platformModule
import com.nesshop.hobito.ui.auth.login.LoginScreen
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication

@Composable
@Preview
fun App() {
    KoinApplication(application = {
        modules(
            platformModule
        )
    }) {
        HobitoTheme {
            LoginScreen()
        }
    }
}