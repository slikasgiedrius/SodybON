package com.giedrius.slikas.sodybon.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.giedrius.slikas.sodybon.data.user.model.User
import org.koin.compose.koinInject

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinInject(),
) {
    val uiState by viewModel.uiState.collectAsState()
    Column {
        Users(uiState.users)
        Text(uiState.articles.toString())
    }
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