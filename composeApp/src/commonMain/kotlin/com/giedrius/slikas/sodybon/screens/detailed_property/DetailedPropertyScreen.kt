package com.giedrius.slikas.sodybon.screens.detailed_property

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.giedrius.slikas.sodybon.compose.base.SodybOnTheme

@Composable
fun DetailedArticle(
    propertyId: String?,
    onNavigateBack: () -> Unit
) {

    SodybOnTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { onNavigateBack() }) {
                Text("Go back")
            }

            if (!propertyId.isNullOrEmpty()) {
                Text(propertyId)
            }
        }
    }
}