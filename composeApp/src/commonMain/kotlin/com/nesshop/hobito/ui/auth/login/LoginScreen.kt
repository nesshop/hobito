package com.nesshop.hobito.ui.auth.login

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nesshop.hobito.Res
import com.nesshop.hobito.add_icon
import com.nesshop.hobito.apple_logo
import com.nesshop.hobito.baloo2_bold
import com.nesshop.hobito.book_icon
import com.nesshop.hobito.chat_icon
import com.nesshop.hobito.designsystem.components.atoms.HobitoButton
import com.nesshop.hobito.designsystem.components.atoms.HobitoCircularIcon
import com.nesshop.hobito.designsystem.components.atoms.HobitoText
import com.nesshop.hobito.designsystem.components.atoms.HobitoTextField
import com.nesshop.hobito.designsystem.components.molecules.HobitoClickableText
import com.nesshop.hobito.designsystem.components.molecules.HobitoColoredTitle
import com.nesshop.hobito.designsystem.theme.bitterSweet
import com.nesshop.hobito.designsystem.theme.dodger_blue
import com.nesshop.hobito.designsystem.theme.golden_tainoi
import com.nesshop.hobito.designsystem.theme.java
import com.nesshop.hobito.designsystem.theme.malibu
import com.nesshop.hobito.designsystem.theme.yellow_orange
import com.nesshop.hobito.domain.usecase.SignInWithEmailUseCase
import com.nesshop.hobito.google_logo
import com.nesshop.hobito.login_screen_apple_logo_content_description
import com.nesshop.hobito.login_screen_apple_sign
import com.nesshop.hobito.login_screen_book_logo_content_description
import com.nesshop.hobito.login_screen_chat_logo_content_description
import com.nesshop.hobito.login_screen_circular_logo_content_description
import com.nesshop.hobito.login_screen_divider_text
import com.nesshop.hobito.login_screen_email_label
import com.nesshop.hobito.login_screen_google_logo_content_description
import com.nesshop.hobito.login_screen_google_sign
import com.nesshop.hobito.login_screen_have_an_account_text
import com.nesshop.hobito.login_screen_login_button
import com.nesshop.hobito.login_screen_password_label
import com.nesshop.hobito.login_screen_sign_up_text
import com.nesshop.hobito.login_screen_title
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

@Preview
@Composable
fun LoginScreen() {

    val signIn = koinInject<SignInWithEmailUseCase>()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

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
                    contentDescription = stringResource(Res.string.login_screen_circular_logo_content_description)
                )
                HobitoCircularIcon(
                    painter = painterResource(Res.drawable.chat_icon),
                    contentDescription = stringResource(Res.string.login_screen_chat_logo_content_description),
                )
                HobitoCircularIcon(
                    painter = painterResource(Res.drawable.book_icon),
                    contentDescription = stringResource(Res.string.login_screen_book_logo_content_description)
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
            Card(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = 0.6f)
                )
            ) {

                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    HobitoTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = stringResource(Res.string.login_screen_email_label),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp)
                    )
                    HobitoTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = stringResource(Res.string.login_screen_password_label),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp)
                    )
                    HobitoButton(
                        text = stringResource(Res.string.login_screen_login_button),
                        onClick = {
                            scope.launch {
                              val result = signIn(email, password)
                                println(result)
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = false
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        HorizontalDivider(modifier = Modifier.weight(1f))
                        HobitoText(
                            text = stringResource(Res.string.login_screen_divider_text),
                            modifier = Modifier.padding(horizontal = 8.dp),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        HorizontalDivider(modifier = Modifier.weight(1f))
                    }

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        OutlinedButton(
                            onClick = {},
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Image(
                                painter = painterResource(Res.drawable.google_logo),
                                contentDescription = stringResource(Res.string.login_screen_google_logo_content_description),
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            HobitoText(
                                text = stringResource(Res.string.login_screen_google_sign)
                            )
                        }
                        OutlinedButton(
                            onClick = {},
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Image(
                                painter = painterResource(Res.drawable.apple_logo),
                                contentDescription = stringResource(Res.string.login_screen_apple_logo_content_description),
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            HobitoText(
                                text = stringResource(Res.string.login_screen_apple_sign)
                            )
                        }
                    }
                }
            }
            HobitoClickableText(fullText = stringResource(Res.string.login_screen_have_an_account_text),
                clickableText = stringResource(Res.string.login_screen_sign_up_text),
                clickableColor = malibu,
                onClickableTextClick = {TODO()})
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
