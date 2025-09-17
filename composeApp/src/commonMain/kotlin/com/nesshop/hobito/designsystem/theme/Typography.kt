package com.nesshop.hobito.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.nesshop.hobito.Poppins_Black
import com.nesshop.hobito.Poppins_BlackItalic
import com.nesshop.hobito.Poppins_Bold
import com.nesshop.hobito.Poppins_BoldItalic
import com.nesshop.hobito.Poppins_ExtraBold
import com.nesshop.hobito.Poppins_ExtraBoldItalic
import com.nesshop.hobito.Poppins_ExtraLight
import com.nesshop.hobito.Poppins_ExtraLightItalic
import com.nesshop.hobito.Poppins_Italic
import com.nesshop.hobito.Poppins_Light
import com.nesshop.hobito.Poppins_LightItalic
import com.nesshop.hobito.Poppins_Medium
import com.nesshop.hobito.Poppins_MediumItalic
import com.nesshop.hobito.Poppins_Regular
import com.nesshop.hobito.Poppins_SemiBold
import com.nesshop.hobito.Poppins_SemiBoldItalic
import com.nesshop.hobito.Poppins_Thin
import com.nesshop.hobito.Poppins_ThinItalic
import com.nesshop.hobito.Res
import com.nesshop.hobito.baloo2_bold
import com.nesshop.hobito.baloo2_extrabold
import com.nesshop.hobito.baloo2_medium
import com.nesshop.hobito.baloo2_regular
import com.nesshop.hobito.baloo2_semibold
import org.jetbrains.compose.resources.Font

@Composable
fun hobitoTypography(): Typography {
    val poppins = FontFamily(
        Font(Res.font.Poppins_Bold, FontWeight.Bold),
        Font(Res.font.Poppins_BoldItalic, FontWeight.Bold, FontStyle.Italic),
        Font(Res.font.Poppins_ExtraBold, FontWeight.ExtraBold),
        Font(Res.font.Poppins_ExtraBoldItalic, FontWeight.ExtraBold, FontStyle.Italic),
        Font(Res.font.Poppins_ExtraLight, FontWeight.ExtraLight),
        Font(Res.font.Poppins_ExtraLightItalic, FontWeight.ExtraLight, FontStyle.Italic),
        Font(Res.font.Poppins_Italic, FontWeight.Normal, FontStyle.Italic),
        Font(Res.font.Poppins_Light, FontWeight.Light),
        Font(Res.font.Poppins_LightItalic, FontWeight.Light, FontStyle.Italic),
        Font(Res.font.Poppins_Medium, FontWeight.Medium),
        Font(Res.font.Poppins_MediumItalic, FontWeight.Medium, FontStyle.Italic),
        Font(Res.font.Poppins_SemiBold, FontWeight.SemiBold),
        Font(Res.font.Poppins_SemiBoldItalic, FontWeight.SemiBold, FontStyle.Italic),
        Font(Res.font.Poppins_Thin, FontWeight.Thin),
        Font(Res.font.Poppins_ThinItalic, FontWeight.Thin, FontStyle.Italic),
        Font(Res.font.Poppins_Black, FontWeight.Black),
        Font(Res.font.Poppins_BlackItalic, FontWeight.Black, FontStyle.Italic),
        Font(Res.font.Poppins_Regular, FontWeight.Normal)
    )


    val baloo2 = FontFamily(
        Font(Res.font.baloo2_bold, FontWeight.Bold),
        Font(Res.font.baloo2_extrabold, FontWeight.ExtraBold),
        Font(Res.font.baloo2_medium, FontWeight.Medium),
        Font(Res.font.baloo2_regular, FontWeight.Normal),
        Font(Res.font.baloo2_semibold, FontWeight.SemiBold)
    )

    return Typography(
        bodyLarge = TextStyle(
            fontFamily = baloo2,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = baloo2,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp
        ),
        bodySmall = TextStyle(
            fontFamily = baloo2,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp
        ),
        titleLarge = TextStyle(
            fontFamily = poppins,
            fontWeight = FontWeight.Normal,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp
        ),
        titleMedium = TextStyle(
            fontFamily = poppins,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.1.sp
        ),
        titleSmall = TextStyle(
            fontFamily = poppins,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp
        ),
        labelLarge = TextStyle(
            fontFamily = baloo2,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp
        ),
        labelMedium = TextStyle(
            fontFamily = baloo2,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp
        ),
        labelSmall = TextStyle(
            fontFamily = baloo2,
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp
        ),
        headlineLarge = TextStyle(
            fontFamily = poppins,
            fontWeight = FontWeight.Normal,
            fontSize = 32.sp,
            lineHeight = 40.sp,
            letterSpacing = 0.sp
        ),
        headlineMedium = TextStyle(
            fontFamily = poppins,
            fontWeight = FontWeight.Normal,
            fontSize = 28.sp,
            lineHeight = 36.sp,
            letterSpacing = 0.sp
        ),
        headlineSmall = TextStyle(
            fontFamily = poppins,
            fontWeight = FontWeight.Normal,
            fontSize = 24.sp,
            lineHeight = 32.sp,
            letterSpacing = 0.sp
        ),
        displayLarge = TextStyle(
            fontFamily = poppins,
            fontWeight = FontWeight.Normal,
            fontSize = 57.sp,
            lineHeight = 64.sp,
            letterSpacing = -0.25.sp
        ),
        displayMedium = TextStyle(
            fontFamily = poppins,
            fontWeight = FontWeight.Normal,
            fontSize = 45.sp,
            lineHeight = 52.sp,
            letterSpacing = 0.sp
        ),
        displaySmall = TextStyle(
            fontFamily = poppins,
            fontWeight = FontWeight.Normal,
            fontSize = 36.sp,
            lineHeight = 44.sp,
            letterSpacing = 0.sp
        )
    )
}