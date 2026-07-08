package com.nesshop.hobito.features.authentication.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nesshop.hobito.features.authentication.ui.login.LoginScreen
import com.nesshop.hobito.features.authentication.ui.register.RegisterScreen
import com.nesshop.hobito.navigation.AuthRoute

@Composable
fun AuthNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AuthRoute.Login) {
        composable<AuthRoute.Login> {
            LoginScreen(
                navigateToRegister = { navController.navigate(AuthRoute.Register) },
                navigateToHome = {
                    // TODO: Revisar si es necesario esta lambda
                })
        }
        composable<AuthRoute.Register> {
            RegisterScreen(
                navigateToLogin = {
                    navController.navigate(AuthRoute.Login) {
                        popUpTo(AuthRoute.Login) { inclusive = true }
                    }
                },
                navigateToHome = {
                    // TODO: Revisar si es necesario esta lambda
                })
        }
    }
}