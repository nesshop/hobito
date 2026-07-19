package com.nesshop.hobito.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nesshop.hobito.designsystem.theme.bitterSweet
import com.nesshop.hobito.navigation.DashboardRoute
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun DashboardBottomBar(
    navController: NavHostController,
    currentDestination: NavDestination?
) {
    val items = listOf(
        BottomNavigationItem("Home", DashboardRoute.Home, Icons.Default.Home),
        BottomNavigationItem("Search", DashboardRoute.Search, Icons.Default.Search),
        BottomNavigationItem("Statistics", DashboardRoute.Statistics, Icons.Default.Assessment),
        BottomNavigationItem("Profile", DashboardRoute.Profile, Icons.Default.Person),
    )

    Box(
        modifier = Modifier.fillMaxWidth()
            .navigationBarsPadding()
            .padding(start = 24.dp, end = 24.dp, bottom = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = NotchedPillShape(notchRadius = 44.dp),
            color = Color.White,
            shadowElevation = 8.dp
        ) {
            Row(modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically) {
                items.take(2).forEach {item ->
                    NavigationItem(item, currentDestination, navController)
                }

                Box(modifier = Modifier.size(64.dp))

                items.takeLast(2).forEach {item ->
                    NavigationItem(item, currentDestination, navController)
                }
            }
        }
        Surface(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = ((-20).dp))
                .size(72.dp),
            shape = CircleShape,
            color = Color.White,
            shadowElevation = 4.dp
        ) {
            Box(contentAlignment = Alignment.Center){
                FloatingActionButton(
                    onClick = { /*TODO: accion de añadir*/ },
                    modifier = Modifier.size(60.dp),
                    shape = CircleShape,
                    containerColor = bitterSweet,
                    contentColor = Color.White,
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add",
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }
    }

}

class NotchedPillShape(private val notchRadius: Dp) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val radiusPx = with(density) {notchRadius.toPx()}
        val cornerRadius = size.height / 2
        val path = Path().apply {
            moveTo(0f, cornerRadius)

            arcTo(
                rect = Rect(0f, 0f, size.height, size.height),
                startAngleDegrees = 180f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )

            val notchStart = (size.width/2) - radiusPx
            lineTo(notchStart, 0f)

            val controlPointX1 = notchStart + (radiusPx * 0.5f)
            val controlPointX2 = (size.width / 2) - (radiusPx * 0.5f)
            val notchBottomY = radiusPx * 1.0f

            cubicTo(
                x1 = controlPointX1, y1 = 0f,
                x2 = controlPointX2, y2 = notchBottomY,
                x3 = size.width / 2, y3 = notchBottomY
            )

            val controlPointX3 = (size.width / 2) + (radiusPx * 0.5f)
            val controlPointX4 = (size.width / 2) + radiusPx - (radiusPx * 0.5f)

            cubicTo(
                x1 = controlPointX3, y1 = notchBottomY,
                x2 = controlPointX4, y2 = 0f,
                x3 = (size.width / 2) + radiusPx, y3 = 0f
            )

            lineTo(size.width - cornerRadius, 0f)

            arcTo(
                rect = Rect(size.width - size.height, 0f, size.width, size.height),
                startAngleDegrees = 270f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )

            arcTo(
                rect = Rect(size.width - size.height, 0f, size.width, size.height),
                startAngleDegrees = 0f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )

            lineTo(cornerRadius, size.height)

            arcTo(
                rect = Rect(0f, 0f, size.height, size.height),
                startAngleDegrees = 90f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )
            close()
        }
        return Outline.Generic(path)
    }
}

@Composable
fun NavigationItem(item: BottomNavigationItem, currentDestination: NavDestination?, navController: NavHostController) {
    val isSelected = currentDestination?.hierarchy?.any{ it.hasRoute(item.route::class)} == true

    Box(
        modifier = Modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(if (isSelected) bitterSweet.copy(0.1f)else Color.Transparent)
            .clickable{
                navController.navigate(item.route) {
                    popUpTo(navController.graph.findStartDestination().id){
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = item.label,
            tint = if (isSelected) bitterSweet else Color.LightGray.copy(0.8f),
            modifier = Modifier.size(24.dp)
        )
    }
}

@Preview
@Composable
fun DashboardBottomBarPreview() {
    val navController = rememberNavController()
    val currentDestination = navController.currentBackStackEntry?.destination

    DashboardBottomBar(
        navController = navController,
        currentDestination = currentDestination
    )
}

data class BottomNavigationItem(
    val label: String,
    val route: DashboardRoute,
    val icon: ImageVector
)