package com.nesshop.hobito.dashboard

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nesshop.hobito.designsystem.components.atoms.HobitoText
import com.nesshop.hobito.designsystem.layouts.HobitoScreen
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
            HobitoScreen(contentPadding = contentPadding) { safePadding: PaddingValues ->
                HobitoText(
                    text = "Pantalla de búsqueda",
                    modifier = Modifier.fillMaxWidth().padding(safePadding).padding(24.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
        composable<DashboardRoute.Statistics> {
            HobitoScreen(contentPadding = contentPadding) { safePadding: PaddingValues ->
                HobitoText(
                    text = "Pantalla de estadísticas",
                    modifier = Modifier.fillMaxWidth().padding(safePadding).padding(24.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
        composable<DashboardRoute.Profile> {
            HobitoScreen(contentPadding = contentPadding) { safePadding: PaddingValues ->
                HobitoText(
                    text = "Pantalla de perfil",
                    modifier = Modifier.fillMaxWidth().padding(safePadding).padding(24.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}