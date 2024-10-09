package com.giedrius.slikas.sodybon.compose.base

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorPalette = lightColorScheme(
    primary = primaryLight,
    primaryContainer = primaryContainerLight,
    secondary = secondaryLight,
    secondaryContainer = secondaryContainerLight,
    onPrimary = onPrimaryLight,
    onSecondary = onSecondaryLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
)

private val DarkColorPalette = darkColorScheme(
    // M3 dark Color parameters
    primary = primaryLight,
    primaryContainer = primaryContainerDark,
    secondary = secondaryDark,
    secondaryContainer = secondaryContainerDark,
    onPrimary = onPrimaryDark,
    onSecondary = onSecondaryDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
)

@Composable
fun SodybOnTheme(
    content: @Composable () -> Unit,
) {
    val appColorScheme = if (isSystemInDarkTheme()) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = appColorScheme,
        shapes = shapes,
        typography = typography,
        content = content,
    )
}