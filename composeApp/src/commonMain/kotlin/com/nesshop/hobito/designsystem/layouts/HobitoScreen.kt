package com.nesshop.hobito.designsystem.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nesshop.hobito.designsystem.components.molecules.FancyBackground

@Composable
fun HobitoScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    showStatusBarProtector: Boolean = true,
    content: @Composable (PaddingValues) -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        FancyBackground(modifier = Modifier.matchParentSize())
        content(contentPadding)
        if (showStatusBarProtector) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(contentPadding.calculateTopPadding() + 8.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.background.copy(0.7f),
                                Color.Transparent
                            )
                        )
                    )
            )
        }
    }
}