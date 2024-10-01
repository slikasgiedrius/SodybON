package com.giedrius.slikas.sodybon

import androidx.compose.runtime.Composable
import com.giedrius.slikas.sodybon.compose.base.SodybOnTheme
import com.giedrius.slikas.sodybon.navigation.BottomBar

@Composable
fun App() {
    SodybOnTheme {
        BottomBar()
    }
}