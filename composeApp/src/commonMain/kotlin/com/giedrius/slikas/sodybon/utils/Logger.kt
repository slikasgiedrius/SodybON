package com.giedrius.slikas.sodybon.utils

import co.touchlab.kermit.Logger
import dev.gitlive.firebase.firestore.FirestoreExceptionCode

object Property {
    fun Logger.logSuccessfulRetrievalOfProperties() {
        Logger.i { "List of properties retrieved successfully" }
    }
    fun Logger.logFailedRetrievalOfProperties(exception: FirestoreExceptionCode) {
        Logger.e("List of properties retrieval failed with exception: ${exception.name}")
    }
}

object Navigation {
    fun logBottomNavigationItemClicked(tabName: String) {
        Logger.i { "Bottom navigation item clicked: $tabName" }
    }
}

object Home {
    fun logSuccessfulLoad() {
        Logger.i { "List loaded successfully" }
    }

    fun logLoadingError(error: String) {
        Logger.i { "List item loading error $error" }
    }

    fun logListItemClicked(propertyName: String) {
        Logger.i { "List item clicked: $propertyName" }
    }
}

object Login {
    fun logLoginWithGoogleSuccessful() {
        Logger.i { "Login with Google successful" }
    }

    fun logLoginFailed(errorMessage: String) {
        Logger.i { "Login failed with result $errorMessage" }
    }
}
