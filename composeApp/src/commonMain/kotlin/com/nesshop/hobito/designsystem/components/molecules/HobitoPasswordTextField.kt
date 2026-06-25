package com.nesshop.hobito.designsystem.components.molecules

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.nesshop.hobito.designsystem.components.atoms.HobitoIconButton
import com.nesshop.hobito.designsystem.components.atoms.HobitoTextField

@Composable
fun HobitoPasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    isVisible: Boolean,
    onVisibilityToggle: () -> Unit,
    modifier: Modifier = Modifier,
    label: String = "",
    enabled: Boolean = true,
    isError: Boolean = false,
    supportingText: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
    shape: Shape = MaterialTheme.shapes.medium
) {
    HobitoTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        shape = shape,
        label = label,
        enabled = enabled,
        isError = isError,
        supportingText = supportingText,
        visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = keyboardOptions,
        trailingIcon = {
            HobitoIconButton(
                icon = if (isVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                onClick = onVisibilityToggle,
                contentDescription = null
            )
        }
    )
}