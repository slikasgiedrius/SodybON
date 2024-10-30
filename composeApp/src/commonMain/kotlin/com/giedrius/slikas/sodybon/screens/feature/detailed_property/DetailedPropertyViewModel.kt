package com.giedrius.slikas.sodybon.screens.feature.detailed_property

import androidx.compose.material3.DateRangePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDateRangePickerState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

data class DetailedPropertyUiState(
    val showDatePicker: Boolean = false,
    val selectedStartDate: Long? = null,
    val selectedEndDate: Long? = null,
    val showBottomSheet: Boolean = false,
)

class DetailedPropertyViewModel : ViewModel(), KoinComponent {
    private val _uiState = MutableStateFlow(DetailedPropertyUiState())
    val uiState: StateFlow<DetailedPropertyUiState> = _uiState.asStateFlow()

    fun setShowDatePicker(showDatePicker: Boolean) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    showDatePicker = showDatePicker
                )
            }
        }
    }

    fun setSelectedStartDate(selectedStartDate: Long?) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    selectedStartDate = selectedStartDate
                )
            }
        }
    }

    fun setSelectedEndDate(selectedEndDate: Long?) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    selectedEndDate = selectedEndDate
                )
            }
        }
    }

    fun setShowBottomSheet(showBottomSheet: Boolean) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    showBottomSheet = showBottomSheet
                )
            }
        }
    }
}