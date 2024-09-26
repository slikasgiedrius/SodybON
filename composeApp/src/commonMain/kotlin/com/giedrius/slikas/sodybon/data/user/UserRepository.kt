package com.giedrius.slikas.sodybon.data.user

import com.giedrius.slikas.sodybon.data.user.model.User

interface UserRepository {
    suspend fun getCurrentUser(): User?
    suspend fun signOut()
}