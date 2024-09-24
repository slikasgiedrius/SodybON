package com.giedrius.slikas.sodybon

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.giedrius.slikas.sodybon.compose.base.SodybOnTheme
import com.giedrius.slikas.sodybon.screens.home.HomeScreen
import com.giedrius.slikas.sodybon.screens.login.LoginScreen
import com.giedrius.slikas.sodybon.screens.login.LoginViewModel
import org.koin.compose.koinInject

@Composable
fun App(loginViewModel: LoginViewModel = koinInject()) {
    val uiState by loginViewModel.uiState.collectAsState()

    SodybOnTheme {
        if (uiState.currentUser == null) {
            LoginScreen()
        } else {
            HomeScreen()
        }
    }
}