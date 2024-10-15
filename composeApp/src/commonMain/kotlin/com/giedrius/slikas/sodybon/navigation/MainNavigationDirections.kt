package com.giedrius.slikas.sodybon.navigation

import androidx.navigation.NavHostController

internal object MainNavigationDirections {
    object Routes {
        const val HOME = Screens.HOME
        const val DETAILED_PROPERTY =
            "${Screens.DETAILED_PROPERTY}/{${DetailedPropertyArgs.PROPERTY_ID}}"
        const val PROFILE = Screens.PROFILE
    }

    object DetailedPropertyArgs {
        const val PROPERTY_ID = "propertyId"
    }

    internal object Screens {
        const val HOME = "Home"
        const val DETAILED_PROPERTY = "Detailed Property"
        const val PROFILE = "Profile"
    }

    object Actions {
        fun NavHostController.openDetailedProperty(propertyId: String) {
            this.navigate("${Screens.DETAILED_PROPERTY}/$propertyId")
        }

        fun NavHostController.navigateBack() = this.popBackStack()
    }
}