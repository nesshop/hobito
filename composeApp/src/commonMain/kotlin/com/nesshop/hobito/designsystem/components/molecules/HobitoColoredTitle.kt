package com.nesshop.hobito.designsystem.components.molecules

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import com.nesshop.hobito.designsystem.components.atoms.HobitoText

@Composable
fun HobitoColoredTitle(
    letters: List<Pair<String, Color>>,
    texStyle: TextStyle = MaterialTheme.typography.headlineLarge,
    modifier: Modifier = Modifier,
    fontFamily: FontFamily
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for ((letter, color) in letters) {
            HobitoText(text = letter, color = color, style = texStyle, fontFamily = fontFamily)
        }
    }
}