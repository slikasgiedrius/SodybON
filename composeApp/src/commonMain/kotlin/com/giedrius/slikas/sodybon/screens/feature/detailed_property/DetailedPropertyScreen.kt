package com.giedrius.slikas.sodybon.screens.feature.detailed_property

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import co.touchlab.kermit.Logger
import com.giedrius.slikas.sodybon.compose.base.SodybOnTheme
import com.giedrius.slikas.sodybon.screens.feature.detailed_property.date_selection.DatePicker
import com.giedrius.slikas.sodybon.screens.feature.detailed_property.date_selection.DateSelectionBottomSheet
import com.giedrius.slikas.sodybon.utils.Property.logClickBackFromDetailedPropertyScreen
import com.giedrius.slikas.sodybon.utils.extensions.topLevelFullScreenBackground
import org.koin.compose.koinInject

@Composable
fun DetailedArticleScreen(
    modifier: Modifier = Modifier,
    propertyId: String?,
    detailedPropertyViewModel: DetailedPropertyViewModel = koinInject(),
    onNavigateBack: () -> Unit,
) {
    val uiState by detailedPropertyViewModel.uiState.collectAsState()

    if (uiState.showDatePicker) {
        DatePicker()
    }
    if (uiState.showBottomSheet) {
        DateSelectionBottomSheet()
    }

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

            if (!propertyId.isNullOrEmpty()) {
                Text(propertyId)
            }
        }
    }
}