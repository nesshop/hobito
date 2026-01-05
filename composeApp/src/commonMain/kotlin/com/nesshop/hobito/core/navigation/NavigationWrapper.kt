package com.nesshop.hobito.core.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nesshop.hobito.core.navigation.Route.Home
import com.nesshop.hobito.core.navigation.Route.Login
import com.nesshop.hobito.core.navigation.Route.Register
import com.nesshop.hobito.features.authentication.ui.login.LoginScreen
import com.nesshop.hobito.features.authentication.ui.register.RegisterScreen

@Composable
fun NavigationWrapper(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Login) {
        composable<Login> {
            LoginScreen(
                navigateToRegister = { navController.navigate(Register) },
                navigateToHome = {
                    navController.navigate(Home) {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
        composable<Register> {
            RegisterScreen(navigateToLogin = {
                navController.navigate(Login) {
                    popUpTo(0) { inclusive = true }
                    launchSingleTop = true
                }
            })
        }

        composable<Home> {
            HomeScreen()
        }
    }
}

@Composable
fun HomeScreen() {
    Text("Welcome to Hobito!")
}