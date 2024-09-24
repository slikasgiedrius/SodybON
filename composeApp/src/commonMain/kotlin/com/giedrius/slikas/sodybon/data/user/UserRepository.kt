package com.giedrius.slikas.sodybon.data.user

import dev.gitlive.firebase.auth.FirebaseUser

interface UserRepository {
    suspend fun getCurrentUser(): FirebaseUser?
    suspend fun signOut(): Unit
}