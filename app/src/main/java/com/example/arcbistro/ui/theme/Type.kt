package com.example.arcbistro.ui.theme

import com.example.arcbistro.R
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp



// Set of Material typography styles to start with

val sora = FontFamily(
    Font(R.font.sora_regular, FontWeight.Normal),
            Font(R.font.sora_medium, FontWeight.Medium),
            Font(R.font.sora_bold, FontWeight.Bold),
            Font(R.font.sora_extrabold, FontWeight.ExtraBold),
            Font(R.font.sora_extralight, FontWeight.ExtraLight),
            Font(R.font.sora_semibold, FontWeight.SemiBold),
            Font(R.font.sora_thin, FontWeight.Thin),
            Font(R.font.sora_light, FontWeight.Light),


)
val AppTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = sora,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    titleLarge = TextStyle(
        fontFamily = sora,
        fontWeight = FontWeight.SemiBold,
        fontSize = 30.sp,
        lineHeight = 38.sp,
        letterSpacing = 1.sp
    ),

    labelMedium = TextStyle(
        fontFamily = sora,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp

    ),


    labelSmall = TextStyle(
        fontFamily = sora,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),

)