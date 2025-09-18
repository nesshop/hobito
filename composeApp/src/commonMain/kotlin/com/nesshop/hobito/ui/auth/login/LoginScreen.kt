package com.nesshop.hobito.ui.auth.login

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.nesshop.hobito.Res
import com.nesshop.hobito.designsystem.components.atoms.HobitoText
import com.nesshop.hobito.login_screen_title
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun LoginScreen() {
    Scaffold {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            HobitoText(
                stringResource(Res.string.login_screen_title),
            )
        }
    }
}