package com.nesshop.hobito.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Gamepad
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nesshop.hobito.Res
import com.nesshop.hobito.book_icon
import com.nesshop.hobito.designsystem.components.atoms.HobitoText
import com.nesshop.hobito.designsystem.components.molecules.FancyBackground
import com.nesshop.hobito.designsystem.theme.bitterSweet
import com.nesshop.hobito.designsystem.theme.golden_tainoi
import com.nesshop.hobito.designsystem.theme.java
import com.nesshop.hobito.designsystem.theme.malibu
import com.nesshop.hobito.designsystem.theme.yellow_orange
import com.nesshop.hobito.domain.model.HomeItem
import com.nesshop.hobito.home_screen_last_completed
import com.nesshop.hobito.home_screen_recent_activity
import com.nesshop.hobito.home_screen_subtitle
import com.nesshop.hobito.home_screen_title
import com.nesshop.hobito.home_screen_view_all
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        viewModel = HomeViewModel(),
        navigateToAllActivities = {},
        navigateToActivityDetails = {})
}

@Composable
fun HomeScreen(
    navigateToAllActivities: () -> Unit,
    navigateToActivityDetails: (HomeItem) -> Unit,
    viewModel: HomeViewModel = koinInject()
) {

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                HomeUiEffect.NavigateToAllActivities -> {
                    navigateToAllActivities()
                }

                is HomeUiEffect.NavigateToActivityDetails -> {
                    navigateToActivityDetails(effect.item)
                }

            }
        }
    }


    Box(modifier = Modifier.fillMaxSize()) {
        FancyBackground(modifier = Modifier.matchParentSize())
        LazyColumn(
            modifier = Modifier.fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(top = 24.dp, bottom = 24.dp)
        ) {
            item {
                HomeHeader(uiState.userName, stringResource(Res.string.home_screen_subtitle))
            }
            item {
                LastCompletedSection(
                    item = uiState.lastCompleted,
                    onViewAllClick = { viewModel.onIntent(HomeIntent.OnViewAllClicked) })
            }
            item {
                RecentActivitySection(activities = uiState.recentActivities, onActivityClick = {
                    viewModel.onIntent(
                        HomeIntent.OnActivityClicked(it)
                    )
                })
            }
        }
    }
}

@Composable
private fun HomeHeader(username: String, message: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                HobitoText(
                    text = stringResource(Res.string.home_screen_title) + " ",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp
                    ),
                )
                HobitoText(
                    text = username,
                    color = bitterSweet,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp
                    )
                )
            }
            HobitoText(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(Color.White)
                .padding(2.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
                    .background(golden_tainoi.copy(0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = golden_tainoi,
                    modifier = Modifier.size(32.dp)
                )
            }
        }

    }
}

@Composable
private fun LastCompletedSection(item: HomeItem?, onViewAllClick: () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            HobitoText(
                text = stringResource(Res.string.home_screen_last_completed),
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
            HobitoText(
                text = stringResource(Res.string.home_screen_view_all),
                modifier = Modifier.clickable { onViewAllClick() },
                color = bitterSweet,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        item?.let {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(28.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(100.dp, 130.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color.DarkGray)
                    ) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(8.dp)
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(golden_tainoi.copy(0.9f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.book_icon),
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(20.dp))
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = yellow_orange.copy(0.1f),
                            contentColor = yellow_orange
                        ) {
                            HobitoText(
                                text = it.category,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                            )
                        }
                        HobitoText(
                            text = it.title,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        )
                        HobitoText(
                            text = it.subtitle,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(top = 8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.CalendarToday,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp),
                                tint = Color.Gray.copy(0.6f)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            HobitoText(
                                text = it.date,
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray.copy(0.6f)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun RecentActivitySection(activities: List<HomeItem>, onActivityClick: (HomeItem) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        HobitoText(
            text = stringResource(Res.string.home_screen_recent_activity),
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )

        activities.forEach { activity ->
            RecentActivityItem(activity, onActivityClick)
        }
    }
}

@Composable
private fun RecentActivityItem(item: HomeItem, onActivityClick: (HomeItem) -> Unit) {
    val iconColor = when (item.category) {
        "MOVIE" -> bitterSweet
        "GAME" -> java
        "SERIES" -> malibu
        else -> golden_tainoi
    }
    val icon = when (item.category) {
        "MOVIE" -> Icons.Default.Movie
        "GAME" -> Icons.Default.Gamepad
        "SERIES" -> Icons.Default.Tv
        else -> Icons.Default.Book
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onActivityClick(item) },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(0.6f))
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
                .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(iconColor.copy(0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconColor,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                HobitoText(
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                HobitoText(
                    text = item.subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
            Column(horizontalAlignment = Alignment.End) {
                item.rating?.let {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = golden_tainoi,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        HobitoText(
                            text = it.toString(),
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                }
                HobitoText(
                    text = item.date,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.LightGray.copy(0.8f)
                )
            }
        }
    }
}

