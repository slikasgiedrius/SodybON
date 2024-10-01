package com.giedrius.slikas.sodybon.data.property.model

import dev.gitlive.firebase.firestore.FirestoreExceptionCode

sealed class GetPropertiesResult {
    data class Success(val properties: List<Property>) : GetPropertiesResult()
    data class Error(val exception: FirestoreExceptionCode) : GetPropertiesResult()
}