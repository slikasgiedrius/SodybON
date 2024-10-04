package com.giedrius.slikas.sodybon.utils.extensions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color

fun Modifier.topLevelFullScreenBackground(backgroundColor: Color): Modifier = composed {
    this
        .fillMaxSize()
        .background(backgroundColor)
//        .windowInsetsPadding(WindowInsets.safeDrawing)
}