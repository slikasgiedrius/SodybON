package com.giedrius.slikas.sodybon.compose.base

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val DarkColorPalette = darkColors(
    primary = primaryDark,
    primaryVariant = primaryContainerDark,
    secondary = secondaryDark,
    secondaryVariant = secondaryContainerDark,
    onPrimary = onPrimaryDark,
    onSecondary = onSecondaryDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
)

private val LightColorPalette = lightColors(
    primary = primaryLight,
    primaryVariant = primaryContainerLight,
    secondary = secondaryLight,
    secondaryVariant = secondaryContainerLight,
    onPrimary = onPrimaryLight,
    onSecondary = onSecondaryLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
)

@Composable
fun SodybOnTheme(
    content: @Composable () -> Unit,
) {
    val colors = if (isSystemInDarkTheme()) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        shapes = shapes,
        typography = typography,
        content = content,
    )
}