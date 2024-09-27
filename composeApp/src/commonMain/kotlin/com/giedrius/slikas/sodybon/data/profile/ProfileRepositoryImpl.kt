package com.giedrius.slikas.sodybon.data.profile

import com.giedrius.slikas.sodybon.data.profile.model.Profile
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth

class ProfileRepositoryImpl(
    private val firebase: Firebase
) : ProfileRepository {

    override suspend fun getCurrentProfile(): Profile? {
        val firebaseUser = firebase.auth.currentUser
        return if (firebaseUser != null) {
            Profile(
                uid = firebaseUser.uid,
                email = firebaseUser.email,
                phoneNumber = firebaseUser.phoneNumber,
                photoUrl = firebaseUser.photoURL,
                firstName = firebaseUser.displayName?.substringBefore(" "),
                lastName = firebaseUser.displayName?.substringAfter(" "),
                fullName = firebase.auth.currentUser?.displayName,
            )
        } else {
            null
        }
    }

    override suspend fun signOut() = firebase.auth.signOut()
}