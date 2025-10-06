package com.nesshop.hobito.designsystem.components.atoms

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import com.nesshop.hobito.Poppins_Regular
import com.nesshop.hobito.Res
import org.jetbrains.compose.resources.Font

@Composable
fun HobitoText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onBackground,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    fontFamily: FontFamily = FontFamily(Font(Res.font.Poppins_Regular))
) {
    Text(text = text, modifier = modifier, color = color, style = style, fontFamily = fontFamily)
}