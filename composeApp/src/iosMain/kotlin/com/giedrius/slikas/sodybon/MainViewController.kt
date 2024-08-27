package com.giedrius.slikas.sodybon

import androidx.compose.ui.window.ComposeUIViewController
import com.giedrius.slikas.sodybon.di.initializeKoin

fun MainViewController() = ComposeUIViewController {
    initializeKoin()
    App()
}