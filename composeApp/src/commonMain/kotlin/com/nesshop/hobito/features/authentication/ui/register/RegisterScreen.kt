package com.nesshop.hobito.features.authentication.ui.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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
import com.nesshop.hobito.designsystem.components.molecules.FancyBackground
import com.nesshop.hobito.designsystem.components.molecules.HobitoClickableText
import com.nesshop.hobito.designsystem.components.molecules.HobitoColoredTitle
import com.nesshop.hobito.designsystem.components.molecules.HobitoPasswordTextField
import com.nesshop.hobito.designsystem.theme.bitterSweet
import com.nesshop.hobito.designsystem.theme.dodger_blue
import com.nesshop.hobito.designsystem.theme.golden_tainoi
import com.nesshop.hobito.designsystem.theme.java
import com.nesshop.hobito.designsystem.theme.malibu
import com.nesshop.hobito.designsystem.theme.yellow_orange
import com.nesshop.hobito.features.authentication.ui.register.contract.RegisterIntent
import com.nesshop.hobito.features.authentication.ui.register.contract.RegisterUiEffect
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
import com.nesshop.hobito.register_screen_already_have_account
import com.nesshop.hobito.register_screen_login_text
import com.nesshop.hobito.register_screen_password_label
import com.nesshop.hobito.register_screen_register_button
import com.nesshop.hobito.register_screen_repeat_password_label
import com.nesshop.hobito.register_screen_title
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@Composable
fun RegisterScreen(
    navigateToLogin: () -> Unit,
    navigateToHome: () -> Unit,
    viewModel: RegisterViewModel = koinInject()
) {

    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                RegisterUiEffect.NavigateToHome -> navigateToHome()
                is RegisterUiEffect.ShowError -> snackbarHostState.showSnackbar(effect.message)
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        FancyBackground(modifier = Modifier.matchParentSize())
        Column(
            modifier = Modifier.fillMaxSize()
                .systemBarsPadding()
                .imePadding()
                .verticalScroll(rememberScrollState()),
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
                stringResource(Res.string.register_screen_title),
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
                        value = uiState.email,
                        onValueChange = { viewModel.onIntent(RegisterIntent.OnEmailChanged(it)) },
                        modifier = Modifier.fillMaxWidth().onFocusChanged {
                            if (!it.isFocused && uiState.email.isNotBlank()) {
                                viewModel.onIntent(RegisterIntent.ValidateEmail(uiState.email))
                            }
                        },
                        shape = RoundedCornerShape(16.dp),
                        label = stringResource(Res.string.login_screen_email_label),
                        enabled = !uiState.isLoading,
                        isError = uiState.emailError != null,
                        supportingText = uiState.emailError?.let { stringResource(it) },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        )
                    )
                    HobitoPasswordTextField(
                        value = uiState.password,
                        onValueChange = { viewModel.onIntent(RegisterIntent.OnPasswordChanged(it)) },
                        isVisible = uiState.isPasswordVisible,
                        onVisibilityToggle = {viewModel.onIntent(RegisterIntent.TogglePasswordVisibility)},
                        modifier = Modifier.fillMaxWidth().onFocusChanged {
                            if (!it.isFocused && uiState.password.isNotBlank()) {
                                viewModel.onIntent(RegisterIntent.ValidatePassword(uiState.password))
                            }
                        },
                        label = stringResource(Res.string.register_screen_password_label),
                        enabled = !uiState.isLoading,
                        isError = uiState.passwordError != null,
                        supportingText = uiState.passwordError?.let { stringResource(it) },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Next
                        ),
                        shape = RoundedCornerShape(16.dp),
                    )
                    HobitoPasswordTextField(
                        value = uiState.repeatPassword,
                        onValueChange = {
                            viewModel.onIntent(
                                RegisterIntent.OnRepeatPasswordChanged(
                                    uiState.password,
                                    it
                                )
                            )
                        },
                        isVisible = uiState.isRepeatPasswordVisible,
                        onVisibilityToggle = {viewModel.onIntent(RegisterIntent.ToggleRepeatPasswordVisibility)},
                        modifier = Modifier.fillMaxWidth().onFocusChanged {
                            if (!it.isFocused && uiState.repeatPassword.isNotBlank()) {
                                viewModel.onIntent(
                                    RegisterIntent.ValidateRepeatPassword(
                                        uiState.password,
                                        uiState.repeatPassword
                                    )
                                )
                            }
                        },
                        label = stringResource(Res.string.register_screen_repeat_password_label),
                        enabled = !uiState.isLoading,
                        isError = uiState.repeatPasswordError != null,
                        supportingText = uiState.repeatPasswordError?.let { stringResource(it) },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        shape = RoundedCornerShape(16.dp),
                    )
                    HobitoButton(
                        text = stringResource(Res.string.register_screen_register_button),
                        onClick = {
                            viewModel.onIntent(
                                RegisterIntent.SubmitRegister(
                                    uiState.email,
                                    uiState.password
                                )
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = uiState.isFormValid && !uiState.isLoading
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
                            onClick = { /*TODO*/ },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            enabled = !uiState.isLoading
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
                            onClick = { /*TODO*/ },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            enabled = !uiState.isLoading
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
            HobitoClickableText(
                fullText = stringResource(Res.string.register_screen_already_have_account),
                clickableText = stringResource(Res.string.register_screen_login_text),
                clickableColor = malibu,
                onClickableTextClick = { if (!uiState.isLoading) navigateToLogin() })
        }

        if (uiState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}
