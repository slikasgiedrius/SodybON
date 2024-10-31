package com.giedrius.slikas.sodybon.navigation

import androidx.navigation.NavHostController

fun navigateToTab(
    navController: NavHostController,
    tab: NavigationBarTabs
) {
    navController.navigate(
        route = tab.name
    ) {
        navController.graph.startDestinationRoute?.let {
            popUpTo(it) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}