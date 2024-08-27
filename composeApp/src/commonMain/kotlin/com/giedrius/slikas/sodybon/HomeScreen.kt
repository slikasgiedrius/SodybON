package com.giedrius.slikas.sodybon

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel { HomeViewModel() },
) {
    val uiState by viewModel.uiState.collectAsState()
    Users(uiState.users)
}

@Composable
fun Users(users: List<User>) {
    users.forEach {
        Column {
            Text(
                text = it.name
            )
            Text(
                text = it.age.toString()
            )
            Text(
                text = it.fullName
            )
        }
    }

}