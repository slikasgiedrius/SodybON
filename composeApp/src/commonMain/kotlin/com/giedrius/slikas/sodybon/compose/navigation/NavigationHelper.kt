package com.giedrius.slikas.sodybon.compose.navigation

import androidx.navigation.NavHostController

fun navigateToTab(
    navController: NavHostController,
    tab: BottomBarTabs
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