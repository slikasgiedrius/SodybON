package com.giedrius.slikas.sodybon.data.user

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.auth

class UserRepositoryImpl(
    private val firebase: Firebase
) : UserRepository {

    override suspend fun getCurrentUser(): FirebaseUser? = firebase.auth.currentUser

    override suspend fun signOut(): Unit = firebase.auth.signOut()
}