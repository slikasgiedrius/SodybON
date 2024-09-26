package com.giedrius.slikas.sodybon.data.user

import com.giedrius.slikas.sodybon.data.user.model.User
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth

class UserRepositoryImpl(
    private val firebase: Firebase
) : UserRepository {

    override suspend fun getCurrentUser(): User? {
        val firebaseUser = firebase.auth.currentUser
        return if (firebaseUser != null) {
            User(
                displayName = firebase.auth.currentUser?.displayName,
                firstName = firebaseUser.displayName?.substringBefore(" "),
                lastName = firebaseUser.displayName?.substringAfter(" "),
            )
        } else {
            null
        }
    }

    override suspend fun signOut() = firebase.auth.signOut()
}