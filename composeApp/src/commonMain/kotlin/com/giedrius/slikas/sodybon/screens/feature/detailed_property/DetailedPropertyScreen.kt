package com.giedrius.slikas.sodybon.screens.feature.detailed_property

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import co.touchlab.kermit.Logger
import com.giedrius.slikas.sodybon.compose.base.SodybOnTheme
import com.giedrius.slikas.sodybon.utils.Property.logClickBackFromDetailedPropertyScreen
import com.giedrius.slikas.sodybon.utils.extensions.topLevelFullScreenBackground

@Composable
fun DetailedArticle(
    modifier: Modifier = Modifier,
    propertyId: String?,
    onNavigateBack: () -> Unit,
    onNavigateToProfile: () -> Unit,
) {

    SodybOnTheme {
        Column(
            modifier = modifier
                .topLevelFullScreenBackground(MaterialTheme.colorScheme.error),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                Logger.logClickBackFromDetailedPropertyScreen()
                onNavigateBack()
            }) {
                Text("Go back")
            }

            Button(onClick = {
                onNavigateToProfile()
            }) {
                Text("Go to profile")
            }

            if (!propertyId.isNullOrEmpty()) {
                Text(propertyId)
            }
        }
    }
}