package com.giedrius.slikas.sodybon.data.property

import com.giedrius.slikas.sodybon.data.property.model.GetPropertiesResult
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.FirebaseFirestoreException
import dev.gitlive.firebase.firestore.code
import dev.gitlive.firebase.firestore.firestore

class PropertyRepositoryImpl(
    private val firestore: Firebase
) : PropertyRepository {
    override suspend fun getProperties(): GetPropertiesResult {
        return try {
            val userResponse = firestore.firestore.collection("PROPERTIES").get()
            GetPropertiesResult.Success(properties = userResponse.documents.map { it.data() })
        } catch (e: FirebaseFirestoreException) {
            GetPropertiesResult.Error(e.code)
        }
    }
}