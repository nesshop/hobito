package com.nesshop.hobito.designsystem.components.atoms

import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HobitoButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = false
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled
    ) {
        HobitoText(text = text)
    }
}