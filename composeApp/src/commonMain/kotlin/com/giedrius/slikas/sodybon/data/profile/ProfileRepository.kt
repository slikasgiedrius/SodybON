package com.giedrius.slikas.sodybon.data.profile

import com.giedrius.slikas.sodybon.data.profile.model.Profile

interface ProfileRepository {
    suspend fun getCurrentProfile(): Profile?
    suspend fun signOut()
}