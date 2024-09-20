package com.giedrius.slikas.sodybon

import androidx.compose.runtime.Composable
import com.giedrius.slikas.sodybon.compose.base.SodybOnTheme
import com.giedrius.slikas.sodybon.screens.home.HomeScreen

@Composable
fun App() {
    SodybOnTheme {
        HomeScreen()
    }
}