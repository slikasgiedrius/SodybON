package com.giedrius.slikas.sodybon.compose.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.giedrius.slikas.sodybon.screens.feature.detailed_property.DetailedPropertyViewModel
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
        if (detailedPropertyUiState.selectedStartDate != null) {
            val fromInstant =
                Instant.fromEpochMilliseconds(detailedPropertyUiState.selectedStartDate!!)
            val fromDateTime =
                fromInstant.toLocalDateTime(TimeZone.currentSystemDefault())
            val from = LocalDate(
                fromDateTime.year,
                fromDateTime.monthNumber,
                fromDateTime.dayOfMonth
            )
            Text(text = "From $from")
        } else {
            Text(text = "Start date not selected")
        }

        if (detailedPropertyUiState.selectedEndDate != null) {
            val toInstant =
                Instant.fromEpochMilliseconds(detailedPropertyUiState.selectedEndDate!!)
            val toDateTime =
                toInstant.toLocalDateTime(TimeZone.currentSystemDefault())
            val to = LocalDate(
                toDateTime.year,
                toDateTime.monthNumber,
                toDateTime.dayOfMonth
            )

            Text(text = "To $to")
        } else {
            Text(text = "End date not selected")
        }
    }
}