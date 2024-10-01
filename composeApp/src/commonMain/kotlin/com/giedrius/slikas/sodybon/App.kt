package com.giedrius.slikas.sodybon

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.giedrius.slikas.sodybon.compose.base.SodybOnTheme
import com.giedrius.slikas.sodybon.navigation.BottomBar
import com.giedrius.slikas.sodybon.screens.login.LoginScreen
import com.giedrius.slikas.sodybon.screens.login.LoginViewModel
import org.koin.compose.koinInject

@Composable
fun App(
    viewModel: LoginViewModel = koinInject(),
) {
    val uiState by viewModel.uiState.collectAsState()
    SodybOnTheme {
        if (uiState.currentProfile == null) {
            LoginScreen()
        } else {
            BottomBar()
        }
    }
}