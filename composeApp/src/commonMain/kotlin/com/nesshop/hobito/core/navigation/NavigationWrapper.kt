package com.nesshop.hobito.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nesshop.hobito.core.navigation.Route.Login
import com.nesshop.hobito.core.navigation.Route.Register
import com.nesshop.hobito.features.authentication.ui.login.LoginScreen
import com.nesshop.hobito.features.authentication.ui.register.RegisterScreen

@Composable
fun NavigationWrapper(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Login){
        composable<Login> {
            LoginScreen()
        }
        composable<Register> {
            RegisterScreen()
        }
    }
}