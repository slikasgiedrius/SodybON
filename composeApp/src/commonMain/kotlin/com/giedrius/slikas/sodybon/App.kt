package com.giedrius.slikas.sodybon

import androidx.compose.runtime.Composable
import com.giedrius.slikas.sodybon.compose.base.SodybOnTheme
import com.giedrius.slikas.sodybon.screens.home.HomeScreen
import com.giedrius.slikas.sodybon.screens.login.LoginScreen
import com.giedrius.slikas.sodybon.screens.login.LoginViewModel
import org.koin.compose.koinInject

@Composable
fun App(loginViewModel: LoginViewModel = koinInject()) {
    SodybOnTheme {
        if (loginViewModel.uiState.value.currentUser == null) {
            LoginScreen()
        } else {
            HomeScreen()
        }
    }
}