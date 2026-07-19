package com.nesshop.hobito.dashboard

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
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
    contentPadding: PaddingValues = PaddingValues(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DashboardRoute.Home,
        modifier = modifier
    ) {
        composable<DashboardRoute.Home> {
            HomeScreen(contentPadding = contentPadding,
                navigateToAllActivities = {navController.navigate(DashboardRoute.AllActivities)},
                navigateToActivityDetails = {
                    // TODO: Implementar la navegación con el item
                })
        }
        composable<DashboardRoute.Search> {
            Text("Pantalla de búsqueda")
        }
        composable<DashboardRoute.Statistics> {
            Text("Pantalla de estadísticas")
        }
        composable<DashboardRoute.Profile> {
            Text("Pantalla de perfil")
        }
    }
}