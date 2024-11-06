package com.giedrius.slikas.sodybon.data.property.model

import dev.gitlive.firebase.firestore.FirestoreExceptionCode

sealed class GetPropertyResult {
    data class Success(val property: Property) : GetPropertyResult()
    data class Error(val exception: FirestoreExceptionCode) : GetPropertyResult()
}