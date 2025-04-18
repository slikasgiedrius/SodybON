package com.giedrius.slikas.sodybon.screens.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.giedrius.slikas.sodybon.data.property.PropertyRepository
import com.giedrius.slikas.sodybon.data.property.model.GetPropertyListResult
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
                    isPropertiesLoading = true,
                    properties = emptyList()
                )
            }

            when (val result = propertyRepository.getPropertyList()) {
                is GetPropertyListResult.Success -> {
                    _uiState.update { currentState ->
                        Logger.logSuccessfulRetrievalOfProperties()
                        currentState.copy(
                            isPropertiesLoading = false,
                            properties = result.properties
                        )
                    }
                }

                is GetPropertyListResult.Error -> {
                    _uiState.update { currentState ->
                        Logger.logFailedRetrievalOfProperties(exception = result.exception)
                        currentState.copy(
                            isPropertiesLoading = false,
                            getPropertiesException = result.exception
                        )
                    }
                }
            }
        }
    }
}