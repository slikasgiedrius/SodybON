package com.giedrius.slikas.sodybon.data.property

import com.giedrius.slikas.sodybon.data.property.model.Property
import com.giedrius.slikas.sodybon.data.user.UserRepository
import com.giedrius.slikas.sodybon.data.user.model.User
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore

class PropertyRepositoryImpl(
    private val firestore: Firebase
) : PropertyRepository {
    override suspend fun getProperties(): List<Property> {
        try {
            val userResponse = firestore.firestore.collection("PROPERTIES").get()
            return userResponse.documents.map {
                it.data()
            }
        } catch (e: Exception) {
            throw e
        }
    }
}