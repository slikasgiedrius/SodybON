package com.giedrius.slikas.sodybon.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giedrius.slikas.sodybon.data.property.PropertyRepository
import com.giedrius.slikas.sodybon.data.property.model.Property
import com.giedrius.slikas.sodybon.data.user.UserRepository
import dev.gitlive.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

data class HomeScreenUiState(
    val properties: List<Property> = emptyList(),
    val currentUser: FirebaseUser? = null,
)

class HomeViewModel(
    private val propertyRepository: PropertyRepository,
    private val userRepository: UserRepository,
) : ViewModel(), KoinComponent {

    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState: StateFlow<HomeScreenUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            updateUser()
            updateProperties()
        }
    }

    fun updateUser() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    currentUser = userRepository.getCurrentUser().value,
                )
            }
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