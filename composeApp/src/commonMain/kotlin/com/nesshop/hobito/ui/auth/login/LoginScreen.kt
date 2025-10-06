package com.nesshop.hobito.ui.auth.login

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.nesshop.hobito.Res
import com.nesshop.hobito.add_icon
import com.nesshop.hobito.baloo2_bold
import com.nesshop.hobito.book_icon
import com.nesshop.hobito.chat_icon
import com.nesshop.hobito.designsystem.components.atoms.HobitoCircularIcon
import com.nesshop.hobito.designsystem.components.atoms.HobitoText
import com.nesshop.hobito.designsystem.components.molecules.HobitoColoredTitle
import com.nesshop.hobito.designsystem.theme.bitterSweet
import com.nesshop.hobito.designsystem.theme.dodger_blue
import com.nesshop.hobito.designsystem.theme.golden_tainoi
import com.nesshop.hobito.designsystem.theme.java
import com.nesshop.hobito.designsystem.theme.malibu
import com.nesshop.hobito.designsystem.theme.yellow_orange
import com.nesshop.hobito.login_screen_title
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun LoginScreen() {
    Box(modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.systemBars)) {
        FancyBackground(modifier = Modifier.matchParentSize())
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                HobitoCircularIcon(
                    painter = painterResource(Res.drawable.add_icon),
                    contentDescription = "Circle logo"
                )
                HobitoCircularIcon(
                    painter = painterResource(Res.drawable.chat_icon),
                    contentDescription = "Chat logo",
                )
                HobitoCircularIcon(
                    painter = painterResource(Res.drawable.book_icon),
                    contentDescription = "Book logo"
                )
            }
            HobitoColoredTitle(
                listOf(
                    "H" to bitterSweet,
                    "o" to golden_tainoi,
                    "b" to malibu,
                    "i" to java,
                    "t" to yellow_orange,
                    "o" to dodger_blue
                ), texStyle = MaterialTheme.typography.displayLarge.copy(fontSize = 64.sp),
                fontFamily = FontFamily(Font(Res.font.baloo2_bold))
            )
            HobitoText(
                stringResource(Res.string.login_screen_title),
            )
        }
    }
}

@Composable
fun FancyBackground(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height
            val minDim = size.minDimension

            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(Color(0xFF42A5F5), Color.Transparent),
                    center = Offset(x = -minDim * 0.05f, y = -minDim * 0.05f),
                    radius = minDim * 0.4f
                ),
                radius = minDim * 0.4f,
                center = Offset(x = -minDim * 0.05f, y = -minDim * 0.05f)
            )

            // círculo abajo derecha (apenas fuera)
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(Color(0xFFFF7043), Color.Transparent),
                    center = Offset(x = width + minDim * 0.05f, y = height + minDim * 0.05f),
                    radius = minDim * 0.45f
                ),
                radius = minDim * 0.45f,
                center = Offset(x = width + minDim * 0.05f, y = height + minDim * 0.05f)
            )

            // círculo central (igual, centrado)
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(Color(0xFFFFEB3B), Color.Transparent),
                    center = Offset(x = width * 0.5f, y = height * 0.5f),
                    radius = minDim * 0.3f
                ),
                radius = minDim * 0.3f,
                center = Offset(x = width * 0.5f, y = height * 0.5f)
            )
        }
    }
}
