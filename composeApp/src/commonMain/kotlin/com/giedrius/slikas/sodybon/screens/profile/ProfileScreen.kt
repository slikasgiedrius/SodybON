package com.giedrius.slikas.sodybon.screens.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.giedrius.slikas.sodybon.compose.base.SodybOnTheme
import com.giedrius.slikas.sodybon.compose.components.GoogleLogin
import com.giedrius.slikas.sodybon.screens.login.LoginViewModel
import org.koin.compose.koinInject

@Composable
fun ProfileScreen(
    loginViewModel: LoginViewModel = koinInject(),
) {
    val uiState by loginViewModel.uiState.collectAsState()

    SodybOnTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column {
                if (uiState.currentUser == null) {
                    GoogleLogin(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        onUpdateUser = { loginViewModel.updateUser() },
                    )
                } else {
                    Text(
                        text = uiState.currentUser?.displayName ?: "Not logged in",
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Button(onClick = { loginViewModel.signOut() }) {
                        Text("Sign out")
                    }
                }
            }
        }
    }
}