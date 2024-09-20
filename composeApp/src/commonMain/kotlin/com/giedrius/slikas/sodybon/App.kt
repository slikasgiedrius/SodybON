package com.giedrius.slikas.sodybon

import androidx.compose.runtime.Composable
import com.giedrius.slikas.sodybon.compose.base.SodybOnTheme
import com.giedrius.slikas.sodybon.screens.login.LoginScreen

@Composable
fun App() {
    SodybOnTheme {
        LoginScreen()
//        HomeScreen()
    }
}