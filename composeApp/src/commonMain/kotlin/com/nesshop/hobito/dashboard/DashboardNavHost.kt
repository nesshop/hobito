package com.nesshop.hobito.dashboard

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nesshop.hobito.features.home.HomeScreen
import com.nesshop.hobito.navigation.DashboardRoute

@Composable
fun DashboardNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DashboardRoute.Home,
        modifier = modifier
    ) {
        composable<DashboardRoute.Home> {
            HomeScreen(navigateToAllActivities = {navController.navigate(DashboardRoute.AllActivities)},
                navigateToActivityDetails = {
                    // TODO: Implementar la navegación con el item 
                })
        }
        composable<DashboardRoute.Search> {
            // TODO: Implementar la pantalla de búsqueda
        }
        composable<DashboardRoute.Statistics> {
            // TODO: Implementar la pantalla de estadísticas
        }
        composable<DashboardRoute.Profile> {
            // TODO: Implementar la pantalla de perfil
        }
    }
}