package com.giedrius.slikas.sodybon.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.giedrius.slikas.sodybon.getPlatform
import org.koin.compose.koinInject

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinInject(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Column {
        Text(getPlatform().name)
        LazyColumn {
            items(uiState.articles) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                ) {
                    Text(
                        text = it.author ?: "No author",
                        modifier = Modifier.padding(16.dp),
                    )
                }
            }
        }
        LazyColumn {
            items(uiState.users) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                ) {
                    Text(
                        text = it.fullName,
                        modifier = Modifier.padding(16.dp),
                    )
                }
            }
        }
    }
}