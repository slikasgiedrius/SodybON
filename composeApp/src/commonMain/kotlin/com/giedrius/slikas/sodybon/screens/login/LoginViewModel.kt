package com.giedrius.slikas.sodybon.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giedrius.slikas.sodybon.data.profile.ProfileRepository
import com.giedrius.slikas.sodybon.data.profile.model.Profile
import com.mmk.kmpauth.google.GoogleAuthCredentials
import com.mmk.kmpauth.google.GoogleAuthProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString
import org.koin.core.component.KoinComponent
import sodybon.composeapp.generated.resources.Res
import sodybon.composeapp.generated.resources.default_web_client_id

data class LoginScreenUiState(
    val currentProfile: Profile? = null,
)

class LoginViewModel(
    private val profileRepository: ProfileRepository,
) : ViewModel(), KoinComponent {

    private val _uiState = MutableStateFlow(LoginScreenUiState())
    val uiState: StateFlow<LoginScreenUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            GoogleAuthProvider.create(
                credentials = GoogleAuthCredentials(
                    serverId = getString(Res.string.default_web_client_id),
                )
            )
            updateUser()
        }
    }

    fun updateUser() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    currentProfile = profileRepository.getCurrentProfile(),
                )
            }

        }
    }

    fun signOut() = viewModelScope.launch {
        profileRepository.signOut()
        updateUser()
    }
}