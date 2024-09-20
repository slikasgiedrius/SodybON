package com.giedrius.slikas.sodybon.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giedrius.slikas.sodybon.data.property.PropertyRepository
import com.giedrius.slikas.sodybon.data.property.model.Property
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

data class HomeScreenUiState(
    val properties: List<Property> = emptyList(),
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
            _uiState.update {
                it.copy(
                    properties = propertyRepository.getProperties(),
                )
            }
        }
    }
}