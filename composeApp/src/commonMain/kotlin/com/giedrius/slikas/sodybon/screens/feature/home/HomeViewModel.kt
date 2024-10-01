package com.giedrius.slikas.sodybon.screens.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.giedrius.slikas.sodybon.data.property.PropertyRepository
import com.giedrius.slikas.sodybon.data.property.model.GetPropertiesResult
import com.giedrius.slikas.sodybon.data.property.model.Property
import com.giedrius.slikas.sodybon.utils.Property.logFailedRetrievalOfProperties
import com.giedrius.slikas.sodybon.utils.Property.logSuccessfulRetrievalOfProperties
import dev.gitlive.firebase.firestore.FirestoreExceptionCode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

data class HomeScreenUiState(
    val isPropertiesLoading: Boolean = true,
    val properties: List<Property> = emptyList(),
    val getPropertiesException: FirestoreExceptionCode? = null
)

class HomeViewModel(
    private val propertyRepository: PropertyRepository,
) : ViewModel(), KoinComponent {

    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState: StateFlow<HomeScreenUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            updateProperties()
        }
    }

    fun updateProperties() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    isPropertiesLoading = true, // Show loading indicator
                    properties = emptyList() // Clear existing properties
                )
            }

            when (val result = propertyRepository.getProperties()) {
                is GetPropertiesResult.Success -> {
                    _uiState.update { currentState ->
                        Logger.logSuccessfulRetrievalOfProperties()
                        currentState.copy(
                            isPropertiesLoading = false, // Hide loading indicator
                            properties = result.properties // Update properties
                        )
                    }
                }

                is GetPropertiesResult.Error -> {
                    _uiState.update { currentState ->
                        Logger.logFailedRetrievalOfProperties(exception = result.exception)
                        currentState.copy(
                            isPropertiesLoading = false, // Hide loading indicator
                            getPropertiesException = result.exception // Show error message
                        )
                    }
                }
            }
        }
    }
}