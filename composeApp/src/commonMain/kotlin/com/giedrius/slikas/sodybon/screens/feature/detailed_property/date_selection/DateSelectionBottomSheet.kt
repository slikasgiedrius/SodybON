package com.giedrius.slikas.sodybon.screens.feature.detailed_property.date_selection

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.giedrius.slikas.sodybon.screens.feature.detailed_property.DetailedPropertyViewModel
import com.giedrius.slikas.sodybon.utils.extensions.toNormalDate
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateSelectionBottomSheet(
    detailedPropertyViewModel: DetailedPropertyViewModel = koinInject()
) {
    val detailedPropertyUiState by detailedPropertyViewModel.uiState.collectAsState()

    ModalBottomSheet(
        onDismissRequest = {
            detailedPropertyViewModel.setShowBottomSheet(false)
        }
    ) {
        DateSelectionBottomSheetItem(
            date = detailedPropertyUiState.selectedStartDate,
            fromText = "From",
            endText = "Start date not selected"
        )

        DateSelectionBottomSheetItem(
            date = detailedPropertyUiState.selectedEndDate,
            fromText = "To",
            endText = "End date not selected"
        )
    }
}