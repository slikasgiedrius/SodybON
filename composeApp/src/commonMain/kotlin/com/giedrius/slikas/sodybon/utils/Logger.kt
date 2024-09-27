package com.giedrius.slikas.sodybon.utils

import co.touchlab.kermit.Logger

object Navigation {
    fun logBottomNavigationItemClicked(tabName: String) {
        Logger.i { "Bottom navigation item clicked: $tabName" }
    }
}

object Home {
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
