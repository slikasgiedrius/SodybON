package com.giedrius.slikas.sodybon.data.property

import co.touchlab.kermit.Logger
import com.giedrius.slikas.sodybon.data.property.model.Property
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.FirebaseFirestoreException
import dev.gitlive.firebase.firestore.firestore

class PropertyRepositoryImpl(
    private val firestore: Firebase
) : PropertyRepository {

    override suspend fun getProperties(): List<Property> {
        return try {
            val userResponse = firestore.firestore.collection("PROPERTIES").get()
            return userResponse.documents.map {
                it.data()
            }
        } catch (e: FirebaseFirestoreException) {
            Logger.e("Error getting properties ${e.message}")
            emptyList()
        }
    }
}