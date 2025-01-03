package com.giedrius.slikas.sodybon.screens.feature.detailed_property

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import co.touchlab.kermit.Logger
import com.giedrius.slikas.sodybon.compose.components.ReserveButton
import com.giedrius.slikas.sodybon.data.property.model.Property
import com.giedrius.slikas.sodybon.navigation.CUSTOM_BOTTOM_BAR_HEIGHT
import com.giedrius.slikas.sodybon.screens.feature.detailed_property.date_selection.DatePicker
import com.giedrius.slikas.sodybon.screens.feature.detailed_property.date_selection.DateSelectionBottomSheet
import com.giedrius.slikas.sodybon.utils.Property.logClickBackFromDetailedPropertyScreen
import com.giedrius.slikas.sodybon.utils.extensions.topLevelFullScreenBackground
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import sodybon.composeapp.generated.resources.Res
import sodybon.composeapp.generated.resources.compose_multiplatform

@Composable
fun DetailedPropertyScreen(
    modifier: Modifier = Modifier,
    propertyId: String?,
    detailedPropertyViewModel: DetailedPropertyViewModel = koinInject(),
    onNavigateBack: () -> Unit,
) {
    val uiState by detailedPropertyViewModel.uiState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        if (!propertyId.isNullOrEmpty()) {
            detailedPropertyViewModel.getProperty(propertyId = propertyId)
        }
    }

    if (uiState.showDatePicker) {
        DatePicker()
    }
    if (uiState.showBottomSheet) {
        DateSelectionBottomSheet()
    }

    Scaffold(bottomBar = {
        ReserveButton(propertyName = propertyId, //change to propertyName
            onReserveButtonClicked = {
                detailedPropertyViewModel.setShowDatePicker(true)
            })
    }) {

        if (uiState.propertyDetails != null) {
            DetailedPropertyScreenContent(
                property = uiState.propertyDetails!!,
                onNavigateBack = onNavigateBack,
                modifier = modifier
            )
        } else {
            //Handle if property is null
        }
    }
}

@Composable
private fun DetailedPropertyScreenContent(
    property: Property,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    logoNotLoadedPlaceholder: Painter = painterResource(Res.drawable.compose_multiplatform)
) {
    Column(
        modifier = modifier
            .topLevelFullScreenBackground(MaterialTheme.colorScheme.secondary)
            .padding(bottom = CUSTOM_BOTTOM_BAR_HEIGHT),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (property.imageUrl.isNullOrEmpty()) {
            Image(
                painter = logoNotLoadedPlaceholder,
                null,
            )
        } else {
            KamelImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .clip(MaterialTheme.shapes.medium),
                resource = asyncPainterResource(property.imageUrl),
                contentDescription = "",
                contentScale = ContentScale.Fit,
                onLoading = { CircularProgressIndicator(it) },
                onFailure = {
                    Column {
                        Text(
                            text = "Failed to load",
                            fontWeight = FontWeight.Bold,
                        )
                    }
                })
        }

        Text(property.name)

        Button(onClick = {
            Logger.logClickBackFromDetailedPropertyScreen()
            onNavigateBack()
        }) {
            Text("Go back")
        }
    }
}