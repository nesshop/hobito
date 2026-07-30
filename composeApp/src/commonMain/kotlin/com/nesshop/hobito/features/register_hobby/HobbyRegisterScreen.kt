package com.nesshop.hobito.features.register_hobby

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Notes
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Gamepad
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nesshop.hobito.Res
import com.nesshop.hobito.book_icon
import com.nesshop.hobito.designsystem.components.atoms.HobitoButton
import com.nesshop.hobito.designsystem.components.atoms.HobitoText
import com.nesshop.hobito.designsystem.components.atoms.HobitoTextField
import com.nesshop.hobito.designsystem.layouts.HobitoScreenLayout
import com.nesshop.hobito.designsystem.theme.HobitoTheme
import com.nesshop.hobito.designsystem.theme.bitterSweet
import com.nesshop.hobito.designsystem.theme.dodger_blue
import com.nesshop.hobito.designsystem.theme.golden_tainoi
import com.nesshop.hobito.designsystem.theme.java
import com.nesshop.hobito.domain.model.HobbyCategory
import com.nesshop.hobito.hobby_register_cancel
import com.nesshop.hobito.hobby_register_category_label
import com.nesshop.hobito.hobby_register_date_label
import com.nesshop.hobito.hobby_register_notes_label
import com.nesshop.hobito.hobby_register_rating_label
import com.nesshop.hobito.hobby_register_save_button
import com.nesshop.hobito.hobby_register_title
import com.nesshop.hobito.hobby_register_title_label
import com.nesshop.hobito.hobby_register_title_placeholder
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@Composable
fun HobbyRegisterRoute(
    onNavigateBack: () -> Unit,
    viewModel: HobbyRegisterViewModel = koinViewModel(),
    contentPadding: PaddingValues = PaddingValues()
) {

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                HobbyRegisterUiEffect.EntrySaved -> {
                    // TODO: Manejar guardado exitoso
                }

                HobbyRegisterUiEffect.NavigateBack -> onNavigateBack()
                is HobbyRegisterUiEffect.ShowError -> {
                    // TODO: Mostrar error
                }
            }
        }
    }

    HobbyRegisterScreen(
        uiState = uiState,
        onIntent = viewModel::onIntent,
        contentPadding = contentPadding
    )
}

@Composable
private fun HobbyRegisterScreen(
    uiState: HobbyRegisterState,
    onIntent: (HobbyRegisterIntent) -> Unit,
    contentPadding: PaddingValues = PaddingValues()
) {
    var showDatePicker by remember { mutableStateOf(false) }

    if (showDatePicker) {
        HobbyDatePicker(
            onDismiss = { showDatePicker = false },
            onDateSelected = { date ->
                onIntent(HobbyRegisterIntent.OnDateChanged(date))
                showDatePicker = false
            }
        )
    }

    HobitoScreenLayout(contentPadding = contentPadding) { safePadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize()
                .consumeWindowInsets(safePadding)
                .imePadding(),
            contentPadding = PaddingValues(
                top = safePadding.calculateTopPadding() + 16.dp,
                bottom = safePadding.calculateBottomPadding() + 16.dp,
                start = 16.dp,
                end = 16.dp
            ),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                HobbyRegisterHeader(
                    onCancel = { onIntent(HobbyRegisterIntent.Cancel) }
                )
            }
            item {
                HobbyCategorySelection(
                    selectedCategory = uiState.category,
                    onCategorySelected = { onIntent(HobbyRegisterIntent.OnCategoryChanged(it)) }
                )
            }
            item {
                HobbyFormCard(
                    title = uiState.title,
                    dateCompleted = uiState.dateCompleted,
                    rating = uiState.rating,
                    onTitleChanged = { onIntent(HobbyRegisterIntent.OnTitleChanged(it)) },
                    onDateClick = { showDatePicker = true },
                    onRatingChanged = { onIntent(HobbyRegisterIntent.OnRatingChanged(it)) }
                )
            }
            item {
                HobbyNotesSection(
                    notes = uiState.notes,
                    isExpanded = uiState.isNotesExpanded,
                    onToggle = { onIntent(HobbyRegisterIntent.ToggleNotesExpansion) },
                    onNotesChanged = { onIntent(HobbyRegisterIntent.OnNotesChanged(it)) }
                )
            }
            item {
                HobitoButton(
                    text = stringResource(Res.string.hobby_register_save_button),
                    onClick = { onIntent(HobbyRegisterIntent.SaveEntry) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    leadingIcon = {
                        Icon(Icons.Default.Save, contentDescription = null, tint = Color.White)
                    }
                )
            }
        }
    }
}

@Composable
private fun HobbyCategorySelection(
    selectedCategory: HobbyCategory,
    onCategorySelected: (HobbyCategory) -> Unit
) {
    Column {
        HobitoText(
            text = stringResource(Res.string.hobby_register_category_label),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            HobbyCategory.entries.forEach { category ->
                CategoryItem(
                    category = category,
                    isSelected = selectedCategory == category,
                    onClick = { onCategorySelected(category) }
                )
            }
        }
    }
}

@Composable
private fun CategoryItem(
    category: HobbyCategory,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val color = when (category) {
        HobbyCategory.MOVIE -> bitterSweet
        HobbyCategory.SERIES -> dodger_blue
        HobbyCategory.BOOK -> golden_tainoi
        HobbyCategory.GAME -> java
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(color.copy(alpha = 0.2f))
                .let {
                    if (isSelected) it.border(2.dp, Color.Black, RoundedCornerShape(20.dp)) else it
                },
            contentAlignment = Alignment.Center
        ) {
            val iconPainter = when (category) {
                HobbyCategory.MOVIE -> rememberVectorPainter(Icons.Default.Movie)
                HobbyCategory.SERIES -> rememberVectorPainter(Icons.Default.Tv)
                HobbyCategory.BOOK -> painterResource(Res.drawable.book_icon)
                HobbyCategory.GAME -> rememberVectorPainter(Icons.Default.Gamepad)
            }
            Icon(
                painter = iconPainter,
                contentDescription = null,
                tint = color,
                modifier = Modifier
                    .size(32.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        HobitoText(
            text = stringResource(category.toStringResource()),
            style = MaterialTheme.typography.labelSmall,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Composable
private fun HobbyFormCard(
    title: String,
    dateCompleted: LocalDate?,
    rating: Int,
    onTitleChanged: (String) -> Unit,
    onDateClick: () -> Unit,
    onRatingChanged: (Int) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            //Title
            HobitoText(
                text = stringResource(Res.string.hobby_register_title_label),
                style = MaterialTheme.typography.labelMedium,
                color = Color.LightGray,
                fontWeight = FontWeight.Bold
            )
            HobitoTextField(
                value = title,
                onValueChange = onTitleChanged,
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(Res.string.hobby_register_title_placeholder),
                shape = RoundedCornerShape(0.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))

            //Date
            HobitoText(
                text = stringResource(Res.string.hobby_register_date_label),
                style = MaterialTheme.typography.labelMedium,
                color = Color.LightGray,
                fontWeight = FontWeight.Bold
            )
            Box(modifier = Modifier.clickable { onDateClick() }) {
                HobitoTextField(
                    value = dateCompleted?.toUiString() ?: "Select date",
                    onValueChange = {},
                    enabled = false,
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        Icon(
                            Icons.Default.CalendarMonth,
                            contentDescription = null,
                            tint = Color.Gray
                        )
                    }
                )
            }
            Spacer(modifier = Modifier.height(24.dp))

            //Rating
            HobitoText(
                text = stringResource(Res.string.hobby_register_rating_label),
                style = MaterialTheme.typography.labelMedium,
                color = Color.LightGray,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                (1..5).forEach { index ->
                    Icon(
                        imageVector = if (index <= rating) Icons.Filled.Star else Icons.Outlined.StarBorder,
                        contentDescription = null,
                        tint = if (index <= rating) golden_tainoi else Color.LightGray,
                        modifier = Modifier
                            .size(32.dp)
                            .clickable { onRatingChanged(index) }
                    )
                }
            }
        }
    }
}

@Composable
private fun HobbyNotesSection(
    notes: String,
    isExpanded: Boolean,
    onToggle: () -> Unit,
    onNotesChanged: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onToggle() },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color(0xFFF5F5F5), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Notes,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = Color.Gray
                    )
                }
                HobitoText(
                    text = stringResource(Res.string.hobby_register_notes_label),
                    modifier = Modifier.weight(1f),
                    fontWeight = FontWeight.Medium
                )
                Icon(
                    imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = Color.Gray
                )
            }
            AnimatedVisibility(visible = isExpanded) {
                HobitoTextField(
                    value = notes,
                    onValueChange = onNotesChanged,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    label = "Write something...",
                    shape = RoundedCornerShape(12.dp)
                )
            }
        }
    }
}


@Composable
private fun HobbyRegisterHeader(onCancel: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        HobitoText(
            text = stringResource(Res.string.hobby_register_title),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        HobitoText(
            text = stringResource(Res.string.hobby_register_cancel),
            modifier = Modifier.clickable { onCancel() },
            color = Color.Gray
        )
    }
}

@OptIn(ExperimentalTime::class)
@Composable
private fun HobbyDatePicker(
    onDismiss: () -> Unit,
    onDateSelected: (LocalDate) -> Unit
) {

    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    datePickerState.selectedDateMillis?.let { millis ->
                        val date = Instant
                            .fromEpochMilliseconds(millis)
                            .toLocalDateTime(TimeZone.currentSystemDefault())
                            .date

                        onDateSelected(date)
                        onDismiss()
                    }
                }
            ) {
                HobitoText(text = "OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss){
                HobitoText(text = "Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}


@Preview
@Composable
private fun RegisterHobbyScreenPreview() {
    HobitoTheme {
        HobbyRegisterScreen(
            uiState = HobbyRegisterState(
                title = "Mi nuevo Hobby",
                category = HobbyCategory.GAME
            ),
            onIntent = { }
        )
    }
}
