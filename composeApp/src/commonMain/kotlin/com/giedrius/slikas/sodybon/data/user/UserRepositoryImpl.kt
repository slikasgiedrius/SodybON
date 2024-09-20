package com.giedrius.slikas.sodybon.data.user

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserRepositoryImpl(
    firebase: Firebase
) : UserRepository {
    private val _currentUser = MutableStateFlow(firebase.auth.currentUser)
    private val currentUser: StateFlow<FirebaseUser?> = _currentUser.asStateFlow()

    override suspend fun getCurrentUser(): StateFlow<FirebaseUser?> = currentUser
}