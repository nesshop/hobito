package com.nesshop.hobito

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import com.nesshop.hobito.core.di.authModule
import com.nesshop.hobito.core.di.platformModule
import com.nesshop.hobito.core.di.viewModelModule
import com.nesshop.hobito.navigation.NavigationWrapper
import com.nesshop.hobito.designsystem.theme.HobitoTheme
import com.nesshop.hobito.root.RootViewModel
import com.nesshop.hobito.root.contract.RootState
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

            if (state is RootState.Loading) {
                // TODO: Add Splash Screen?
                return@HobitoTheme
            }

            val navController = rememberNavController()
            NavigationWrapper(navController, state)
        }
    }
}
