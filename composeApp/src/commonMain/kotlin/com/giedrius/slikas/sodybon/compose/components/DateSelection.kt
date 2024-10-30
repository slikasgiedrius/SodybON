package com.giedrius.slikas.sodybon.compose.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.giedrius.slikas.sodybon.screens.feature.detailed_property.DetailedPropertyViewModel
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateSelection(
    detailedPropertyViewModel: DetailedPropertyViewModel = koinInject()
) {
    val detailedPropertyUiState by detailedPropertyViewModel.uiState.collectAsState()

    val datePickerState = rememberDateRangePickerState(
        initialSelectedStartDateMillis = Clock.System.now().toEpochMilliseconds(),
        initialDisplayMode = DisplayMode.Picker,
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                val currentDateMillis = Clock.System.now().toEpochMilliseconds()
                return utcTimeMillis >= currentDateMillis - 86400000 // Allow only dates greater than or equal to today
            }

            override fun isSelectableYear(year: Int): Boolean {
                return true
            }
        }
    )
    if (detailedPropertyUiState.showDatePicker) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
        ) {

            if (detailedPropertyUiState.showDatePicker) {
                DatePickerDialog(
                    onDismissRequest = {
                        detailedPropertyViewModel.setShowDatePicker(false)
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                detailedPropertyViewModel.setShowDatePicker(false)
                                detailedPropertyViewModel.setShowBottomSheet(true)
                            },
                        ) {
                            Text("Ok")
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = {
                                detailedPropertyViewModel.setShowDatePicker(false)
                            },
                        ) {
                            Text("Cancel")
                        }
                    }
                ) {
                    detailedPropertyViewModel.setSelectedStartDate(datePickerState.selectedStartDateMillis)
                    detailedPropertyViewModel.setSelectedEndDate(datePickerState.selectedEndDateMillis)
                    DateRangePicker(
                        state = datePickerState,
                    )
                }
            }
        }
    }
}