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
    primary = Color.Black,
    onPrimary = Color.White,
    background = Color.Black
)

private val LightColorPalette = lightColors(
    primary = Color.White,
    onPrimary = Color.Black,
    background = Color.White
)

@Composable
fun TobTheme(
    content: @Composable () -> Unit,
) {
    val colors = if (isSystemInDarkTheme()) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        shapes = MaterialTheme.shapes.copy(
            small = RoundedCornerShape(8.dp),
            medium = RoundedCornerShape(8.dp),
            large = RoundedCornerShape(8.dp),
        )
    ) {
        content()
    }
}