package com.nesshop.hobito.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val LightColorTheme = lightColorScheme(

)

val DarkColorTheme = darkColorScheme(

)

//Custom color function
@Composable
fun extendedColor(lightColor: Color, darkColor: Color) : Color {
    return if (isSystemInDarkTheme()) darkColor else lightColor
}

val ColorScheme.extraColor: Color @Composable get() = extendedColor(
    lightColor = Color.White,
    darkColor = Color.Black
)

@Composable
fun HobitoTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = if (isSystemInDarkTheme()) DarkColorTheme else LightColorTheme
    MaterialTheme(
        colorScheme = colorScheme,
        shapes = shapes,
        typography = Typography,
        content = content
    )
}