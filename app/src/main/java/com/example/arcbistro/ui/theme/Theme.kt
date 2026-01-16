package com.example.arcbistro.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.material3.Typography


private val AppColorScheme = lightColorScheme(
    primary = Brown01,
    secondary = Beige02,
    tertiary = DarkGray03,
    background = White06,
    surface = White06,
    onPrimary = White06,
    onBackground = LightGray04,
    onSurface = LightGray04,

)


@Composable
fun ArcBistroTheme(

    content: @Composable () -> Unit
) {

    MaterialTheme(
        colorScheme = AppColorScheme,
        typography = AppTypography,
        content = content
    )
}