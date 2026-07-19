package com.nesshop.hobito.dashboard

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nesshop.hobito.navigation.DashboardRoute

private val mainRoutes = setOf(
    DashboardRoute.Home::class,
    DashboardRoute.Search::class,
    DashboardRoute.Statistics::class,
    DashboardRoute.Profile::class
)

@Composable
fun DashboardScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val showBottomBar = mainRoutes.any { routeClass ->
        currentDestination?.hierarchy?.any { it.hasRoute(routeClass) } == true
    }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                DashboardBottomBar(
                    navController = navController,
                    currentDestination = currentDestination
                )
            }
        }
    ) { innerPadding ->
        DashboardNavHost(
            navController = navController,
            contentPadding = innerPadding
        )
    }
}
