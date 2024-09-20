package com.giedrius.slikas.sodybon.data.user

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserRepositoryImpl(
    private val firebase: Firebase
) : UserRepository {
    private val _currentUser = MutableStateFlow<FirebaseUser?>(null)
    val currentUser: StateFlow<FirebaseUser?> = _currentUser.asStateFlow()

//    override suspend fun getProperties(): List<Property> {
//        try {
//            val userResponse = firestore.firestore.collection("PROPERTIES").get()
//            return userResponse.documents.map {
//                it.data()
//            }
//        } catch (e: Exception) {
//            throw e
//        }
//    }

    override suspend fun getCurrentUser(): StateFlow<FirebaseUser?> {
        if (firebase.auth.currentUser == null) {
            return currentUser
        } else {
            _currentUser.value = firebase.auth.currentUser
            return currentUser
        }
    }

}