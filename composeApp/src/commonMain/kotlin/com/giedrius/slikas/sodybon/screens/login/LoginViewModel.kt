package com.giedrius.slikas.sodybon.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giedrius.slikas.sodybon.data.user.UserRepository
import com.giedrius.slikas.sodybon.screens.home.HomeScreenUiState
import com.mmk.kmpauth.google.GoogleAuthCredentials
import com.mmk.kmpauth.google.GoogleAuthProvider
import dev.gitlive.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

data class LoginScreenUiState(
    val currentUser: FirebaseUser? = null,
)

class LoginViewModel(
    private val userRepository: UserRepository,
) : ViewModel(), KoinComponent {

    private val _uiState = MutableStateFlow(LoginScreenUiState())
    val uiState: StateFlow<LoginScreenUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            GoogleAuthProvider.create(
                credentials = GoogleAuthCredentials(
                    serverId = "311568248602-advol62mglp8dt0osotd0o1dbodcj9ta.apps.googleusercontent.com"
                )
            )
            updateUser()
        }
    }

    fun updateUser() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    currentUser = userRepository.getCurrentUser(),
                )
            }

        }
    }
}