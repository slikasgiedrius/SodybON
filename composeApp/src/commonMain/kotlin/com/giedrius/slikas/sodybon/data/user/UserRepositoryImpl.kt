package com.giedrius.slikas.sodybon.data.user

import com.giedrius.slikas.sodybon.data.user.model.User
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore

class UserRepositoryImpl(
    private val firestore: Firebase
) : UserRepository {
    override suspend fun getUsers(): List<User> {
        try {
            val userResponse = firestore.firestore.collection("USERS").get()
            return userResponse.documents.map {
                it.data()
            }
        } catch (e: Exception) {
            throw e
        }
    }
}