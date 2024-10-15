package com.giedrius.slikas.sodybon.utils

import co.touchlab.kermit.Logger
import dev.gitlive.firebase.firestore.FirestoreExceptionCode

object Login {
    fun Logger.logLoginWithGoogleClicked() {
        Logger.i { "Login with Google clicked" }
    }

    fun Logger.logLoginWithGoogleSuccessful() {
        Logger.i { "Login with Google successful" }
    }

    fun Logger.logLoginFailed(errorMessage: String) {
        Logger.i { "Login failed with result $errorMessage" }
    }
}

object Navigation {
    fun Logger.logBottomNavigationItemClicked(tabName: String) {
        Logger.i { "Bottom navigation item clicked: $tabName" }
    }
}

object Property {
    fun Logger.logSuccessfulRetrievalOfProperties() {
        Logger.i { "List of properties retrieved successfully" }
    }

    fun Logger.logFailedRetrievalOfProperties(exception: FirestoreExceptionCode) {
        Logger.e("List of properties retrieval failed with exception: ${exception.name}")
    }

    fun Logger.logListItemClicked(propertyName: String) {
        Logger.i { "List item clicked: $propertyName" }
    }

    fun Logger.logClickBackFromDetailedPropertyScreen() {
        Logger.i { "Clicked back from detailed property screen" }
    }

    fun Logger.logClickOnReserveButton(propertyName: String) {
        Logger.i { "Clicked reserve button for property: $propertyName" }
    }

    fun Logger.logUnableToIdentifyPropertyWhenClickingReserve() {
        Logger.i { "Unable to identify property when clicking reserve" }
    }
}

object Profile {
    fun Logger.logOnSignOutClicked() {
        Logger.i { "Sign out clicked" }
    }
}
