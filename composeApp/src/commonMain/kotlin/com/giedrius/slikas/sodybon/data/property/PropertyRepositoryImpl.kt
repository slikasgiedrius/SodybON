package com.giedrius.slikas.sodybon.data.property

import com.giedrius.slikas.sodybon.data.property.model.GetPropertyListResult
import com.giedrius.slikas.sodybon.data.property.model.GetPropertyResult
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.FirebaseFirestoreException
import dev.gitlive.firebase.firestore.code
import dev.gitlive.firebase.firestore.firestore

class PropertyRepositoryImpl(
    private val firestore: Firebase
) : PropertyRepository {

    override suspend fun getPropertyList(): GetPropertyListResult {
        return try {
            val response = firestore.firestore.collection("PROPERTIES").get()
            GetPropertyListResult.Success(properties = response.documents.map { it.data() })
        } catch (e: FirebaseFirestoreException) {
            GetPropertyListResult.Error(e.code)
        }
    }

    override suspend fun getProperty(propertyId: String): GetPropertyResult {
        return try {
            val response = firestore.firestore.collection("PROPERTIES/").document(propertyId).get()
            GetPropertyResult.Success(property = response.data())
        } catch (e: FirebaseFirestoreException) {
            GetPropertyResult.Error(e.code)
        }
    }
}