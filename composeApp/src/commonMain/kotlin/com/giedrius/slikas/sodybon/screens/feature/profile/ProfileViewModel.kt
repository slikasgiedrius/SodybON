package com.giedrius.slikas.sodybon.screens.feature.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.giedrius.slikas.sodybon.data.profile.ProfileRepository
import com.giedrius.slikas.sodybon.utils.Profile.logOnSignOutClicked
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class ProfileViewModel(
    private val profileRepository: ProfileRepository,
) : ViewModel(), KoinComponent {

//    fun getCurrentProfile() {
//        viewModelScope.launch {
//            _uiState.update {
//                it.copy(
//                    currentProfile = profileRepository.getCurrentProfile(),
//                )
//            }
//        }
//    }

    fun signOut() = viewModelScope.launch {
        Logger.logOnSignOutClicked()
        profileRepository.signOut()
        profileRepository.getCurrentProfile()
    }
}