package com.giedrius.slikas.sodybon.screens.feature.detailed_property

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.giedrius.slikas.sodybon.data.property.PropertyRepository
import com.giedrius.slikas.sodybon.data.property.model.GetPropertyResult
import com.giedrius.slikas.sodybon.data.property.model.Property
import com.giedrius.slikas.sodybon.utils.Property.logFailedRetrievalOfDetailedProperty
import com.giedrius.slikas.sodybon.utils.Property.logSuccessfulRetrievalOfProperty
import dev.gitlive.firebase.firestore.FirestoreExceptionCode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

data class DetailedPropertyUiState(
    val isPropertyDetailsLoading: Boolean = true,
    val getPropertyDetailsException: FirestoreExceptionCode? = null,
    val propertyDetails: Property? = null,
    val showDatePicker: Boolean = false,
    val selectedStartDate: Long? = null,
    val selectedEndDate: Long? = null,
    val showBottomSheet: Boolean = false,
)

class DetailedPropertyViewModel(
    private val propertyRepository: PropertyRepository,
) : ViewModel(), KoinComponent {
    private val _uiState = MutableStateFlow(DetailedPropertyUiState())
    val uiState: StateFlow<DetailedPropertyUiState> = _uiState.asStateFlow()

    fun getProperty(propertyId: String) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    isPropertyDetailsLoading = true,
                    propertyDetails = null
                )
            }

            when (val result = propertyRepository.getProperty(propertyId = propertyId)) {
                is GetPropertyResult.Success -> {
                    _uiState.update { currentState ->
                        Logger.logSuccessfulRetrievalOfProperty(result.property.name)
                        currentState.copy(
                            isPropertyDetailsLoading = false,
                            propertyDetails = result.property
                        )
                    }
                }

                is GetPropertyResult.Error -> {
                    _uiState.update { currentState ->
                        Logger.logFailedRetrievalOfDetailedProperty(exception = result.exception)
                        currentState.copy(
                            isPropertyDetailsLoading = false,
                            getPropertyDetailsException = result.exception
                        )
                    }
                }
            }
        }
    }

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