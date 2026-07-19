package com.nesshop.hobito.designsystem.components.molecules

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import com.nesshop.hobito.Poppins_Regular
import com.nesshop.hobito.Res
import org.jetbrains.compose.resources.Font

private const val CLICKABLE_TAG = "CLICKABLE"
private const val CLICKABLE_ANNOTATION = "clickable_action"

@Composable
fun HobitoClickableText(
    fullText: String,
    clickableText: String,
    onClickableTextClick: () -> Unit,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onBackground,
    clickableColor: Color = MaterialTheme.colorScheme.primary,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    fontFamily: FontFamily = FontFamily(Font(Res.font.Poppins_Regular)),
    clickableStyle: SpanStyle = SpanStyle(
        fontWeight = FontWeight.Bold
    ),
    textAlign: TextAlign? = null
) {
    val annotatedString = buildAnnotatedString {
        val startIndex = fullText.indexOf(clickableText)

        append(fullText.substring(0, startIndex))

        pushStringAnnotation(
            tag = CLICKABLE_TAG,
            annotation = CLICKABLE_ANNOTATION
        )
        withStyle(
            style = clickableStyle.copy(color = clickableColor)
        ) {
            append(clickableText)
        }
        pop()

        if (startIndex + clickableText.length < fullText.length) {
            append(fullText.substring(startIndex + clickableText.length))
        }
    }

    var textLayoutResult by remember { mutableStateOf<TextLayoutResult?>(null) }

    Text(
        text = annotatedString,
        modifier = modifier.pointerInput(Unit) {
            detectTapGestures { offset ->
                textLayoutResult?.let { layoutResult ->
                    val position = layoutResult.getOffsetForPosition(offset)
                    annotatedString.getStringAnnotations(
                        tag = CLICKABLE_TAG,
                        start = position,
                        end = position
                    ).firstOrNull()?.let {
                        onClickableTextClick()
                    }
                }
            }
        },
        color = color,
        style = style,
        fontFamily = fontFamily,
        textAlign = textAlign,
        onTextLayout = { textLayoutResult = it }
    )
}