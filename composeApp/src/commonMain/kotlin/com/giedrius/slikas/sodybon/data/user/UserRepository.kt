package com.giedrius.slikas.sodybon.data.user

import dev.gitlive.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.StateFlow

interface UserRepository {
    suspend fun getCurrentUser(): StateFlow<FirebaseUser?>
}