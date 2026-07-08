package com.nesshop.hobito

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.nesshop.hobito.core.di.authModule
import com.nesshop.hobito.core.di.platformModule
import com.nesshop.hobito.core.di.viewModelModule
import com.nesshop.hobito.designsystem.theme.HobitoTheme
import com.nesshop.hobito.root.RootScreen
import com.nesshop.hobito.root.RootViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    KoinApplication(application = {
        modules(
            platformModule, authModule, viewModelModule
        )
    }) {
        HobitoTheme {
            val viewModel = koinViewModel<RootViewModel>()
            val state by viewModel.uiState.collectAsState()

            RootScreen(state)
        }
    }
}
