package com.giedrius.slikas.sodybon.data.property.model

import dev.gitlive.firebase.firestore.FirestoreExceptionCode

sealed class GetPropertyListResult {
    data class Success(val properties: List<Property>) : GetPropertyListResult()
    data class Error(val exception: FirestoreExceptionCode) : GetPropertyListResult()
}