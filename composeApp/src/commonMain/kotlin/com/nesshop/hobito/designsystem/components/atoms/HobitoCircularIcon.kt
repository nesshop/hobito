package com.nesshop.hobito.designsystem.components.atoms

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun HobitoCircularIcon(
    painter: Painter,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    size: Dp = 32.dp,
    backgroundColor: Color = Color.White,
    onClick: (() -> Unit)? = null
) {
    val clickableModifier = if (onClick != null) {
        modifier.clickable { onClick() }
    } else {
        modifier
    }
    Box(
        modifier = clickableModifier.size(size).shadow(8.dp, shape = CircleShape).clip(CircleShape).background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Image(painter = painter,
            contentDescription = contentDescription,
            modifier = Modifier.size(size * 0.6f))
    }
}